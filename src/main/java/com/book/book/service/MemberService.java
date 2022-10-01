package com.book.book.service;


import com.book.book.model.Book;
import com.book.book.model.Member;
import com.book.book.model.Record;
import com.book.book.model.User;
import com.book.book.repository.BookRepository;
import com.book.book.repository.MemberRepository;
import com.book.book.repository.RecordRepository;
import com.book.book.service.ex.AccessDeniedException;
import com.book.book.service.ex.FoundException;
import com.book.book.service.ex.InsertException;
import com.book.book.service.ex.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RecordRepository recordRepository;



    public void save(Member user, String action){


        Record record = new Record();


        record.setSubject(user.getUsername());
        Date now = new Date();
        record.setCreatedTime(new Date());
        record.setDate(new Date());
        record.setAction(action);
        System.out.println(record.getCreatedTime());
        recordRepository.save(record);


    }


    public List<Member> findUndeleteUsers(){
        List<Member> members = memberRepository.findUndeleteUsers();

        if( members==null){//find
            throw  new FoundException("No user");

        }

        return  members;

    }


    public Member delete( User user,Member member){

        Long Id = member.getId();
        if(user.getrk()==3){
            throw new AccessDeniedException(user.getUsername()+" has no authority");
        }

        Optional<Member> u = memberRepository.findById(Id);


        if(u==null){
            throw new FoundException("does not find the Member");
        }
//        Member result = u.get();
//        result.setBalance(member.getBalance());
//        result.setUsername(member.getUsername());
        Member r =  memberRepository.save(member);

        save(r,"delete");

        return r;


    }


    public Member update( User user,Member member){

        Long Id = member.getId();
        if(user.getrk()==3){
            throw new AccessDeniedException(user.getUsername()+" has no authority");
        }

        Optional<Member> u = memberRepository.findById(Id);


        if(u==null){
            throw new FoundException("does not find the Member");
        }
        Member result = u.get();
        result.setBalance(member.getBalance());
        result.setUsername(member.getUsername());
        Member r =  memberRepository.save(result);

        save(r,"update");

        return r;


    }


    public int pay(Member member, Book book){
        long account = member.getBalance();

        if(book==null){
            throw new FoundException("No book provided");
        }

        Optional<Book> op = bookRepository.findById(book.getId());
        Book b = op.get();

        if(b.getPrice()>member.getBalance()){
            return 0;
        }else{
            long p = member.getBalance()-b.getPrice();
            member.setBalance(p);

            memberRepository.save(member);
//            save(member,"pay the money");

            return 1;
        }




    }


    public Member register(Member member) {
        // 根据参数Member对象获取注册的用户名
        String name = member.getUsername();
        // 调用持久层的Member findByMembername(String Membername)方法，根据用户名查询用户数据
        Member result =memberRepository.findMemberByUsername(name);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：表示用户名已被占用，则抛出MembernameDuplicateException异常
            throw new UsernameDuplicateException("[" + name + "] exists");
        }

        // 创建当前时间对象
        Date now = new Date();
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(member.getPassword(), salt);
        member.setPassword(md5Password);
        // 补全数据：盐值
        member.setSalt(salt);
        // 补全数据：isDelete(0)
        member.setIsDelete(0);
        // 补全数据：4项日志属性
        member.setCreatedUser(name);
        member.setCreatedTime(now);
        member.setModifiedUser(name);
        member.setModifiedTime(now);
        member.setUuid( UUID.randomUUID().toString());

        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(Member Member)方法，执行注册并获取返回值(受影响的行数)

//        memberRepository.save(member)
        Member rows = memberRepository.save(member);
        save(rows,"register");
        // 判断受影响的行数是否不为1
        if (rows == null) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("WTF Exception");
        }

        return rows;
    }

    /**
     * 执行密码加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    public Member login(String username, String password) {
        // 调用memberRepository的findByMembername()方法，根据参数Membername查询用户数据
        Member result = memberRepository.findMemberByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出MemberNotFoundException异常
            throw new FoundException("No Member");
        }

//        getMd5Password(Member.getPassword(), salt);

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            // 是：抛出MemberNotFoundException异常
            throw new FoundException("user does not exist");
        }

//        String
        // 从查询结果中获取
        String salt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = getMd5Password(password, salt);

        System.out.println(md5Password);
        System.out.println(result.getPassword());
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new FoundException("error password");
        }

        // 创建新的Member对象
        Member Member = new Member();
        // 将查询结果中的uid、Membername、avatar封装到新的Member对象中
        String uuid =UUID.randomUUID().toString();
        Member.setUuid(uuid);
        Member.setUsername(result.getUsername());
//        Member.setAvatar(result.getAvatar());
//        Member.setModifiedTime(result.);
        // 返回新的Member对象
        return Member;
    }




    public void changePassword(Integer uid, String oldPassword, String newPassword) {
        // 调用memberRepository的findByUid()方法，根据参数uid查询用户数据
        Member result = memberRepository.findMemberById(uid);
        // 检查查询结果是否为null

        if (uid == 0) {
            // 是：抛出MemberNotFoundException异常
            throw new FoundException("no Member");
        }


        if (result == null) {
            // 是：抛出MemberNotFoundException异常
            throw new FoundException("no Member");
        }

        // 检查查询结果中的isDelete是否为1
        if (result.getIsDelete().equals(1)) {
            // 是：抛出MemberNotFoundException异常
            throw new FoundException("No Member");
        }

        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数oldPassword结合盐值加密，得到oldMd5Password
        String oldMd5Password = getMd5Password(oldPassword, salt);
        // 判断查询结果中的password与oldMd5Password是否不一致
        if (!result.getPassword().contentEquals(oldMd5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new FoundException("wrong original password");
        }

        // 将参数newPassword结合盐值加密，得到newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);
        // 创建当前时间对象
        Date now = new Date();
        result.setModifiedTime(now);
        result.setPassword(newMd5Password);
        // 调用memberRepository的updatePasswordByUid()更新密码，并获取返回值
        Member u = memberRepository.save(result);


        // 判断以上返回的受影响行数是否不为1
        if (u == null) {
            // 是：抛出UpdateException异常
            throw new FoundException("error in updating");
        }

        save(u,"change the pass");
    }


}
