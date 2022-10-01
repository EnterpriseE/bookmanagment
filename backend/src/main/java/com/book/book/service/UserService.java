package com.book.book.service;

//import com.book.book.model.user;
import com.book.book.model.Book;
import com.book.book.model.Member;
import com.book.book.model.Record;
import com.book.book.model.User;
import com.book.book.repository.RecordRepository;
import com.book.book.repository.UserRepository;
import com.book.book.service.ex.AccessDeniedException;
import com.book.book.service.ex.FoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordRepository recordRepository;


//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    public List<User> findUndeleteUsers(){
        List<User> users = userRepository.findUndeleteUsers();

        if(users==null || users.size()==0){//find
            throw  new FoundException("No user");

        }

        return users;

    }

    public void save(User user, String action){


        Record record = new Record();


        record.setSubject(user.getUsername());
        record.setDate(new Date());
        record.setAction(action);

        System.out.println(record.toString());
        recordRepository.save(record);


    }



    public User update( User user,User user2){

        Long Id = user2.getId();
        if(user.getrk()!=1){
            throw new AccessDeniedException(user.getUsername()+" has no authority");
        }

        Optional<User> u = userRepository.findById(Id);


        if(u==null){
            throw new FoundException("does not find the user");
        }
        User result = u.get();

        result.setrk(user2.getrk());
        result.setEmail(user2.getEmail());
        result.setUsername(user2.getUsername());
        result.setBrief(user2.getBrief());
        result.setIsDelete(0);
//        result.setUsername(member.getUsername());
        User r =  userRepository.save(result);
        save(r,"update");
        return result;


    }

    public User delete( User user,User user2){

        Long Id = user2.getId();
        if(user.getrk()!=1){
            throw new AccessDeniedException(user.getUsername()+" has no authority");
        }

        Optional<User> u = userRepository.findById(Id);


        if(u==null){
            throw new FoundException("does not find the user");
        }
        User result = u.get();

        result.setIsDelete(1);
//        result.setrk(user2.getrk());
//        result.setEmail(user2.getEmail());
//        result.setUsername(user2.getUsername());
//        result.setBrief(user2.getBrief());
//        result.setUsername(member.getUsername());
        User r =  userRepository.save(result);
        save(r,"update");
        return result;


    }
    public User register(User user) {
//        User.setUuid(UUID.randomUUID());


        User u = userRepository.findUserByUsername(user.getUsername());
        if(u!=null){//find
            return null;
        }

        Date now = new Date();
        // 补全数据：加密后的密码
//        String salt = UUID.randomUUID().toString().toUpperCase();
//        String md5Password = getMd5Password(user.getPassword(), salt);
//        user.setPassword(md5Password);
//        // 补全数据：盐值
//        user.setSalt(salt);
//        // 补全数据：isDelete(0)
//        user.setIsDelete(0);
//        // 补全数据：4项日志属性
////        user.setCreatedUser(user.getUsername());
////        user.setCreatedTime(now);
////        user.setModifiedUser(name);
////        user.setModifiedTime(now);
//        user.setUuid( UUID.randomUUID().toString());

        user.setrk(3);
        user.setIsDelete(0);
        user.setUuid(UUID.randomUUID().toString());


        save(user,"register");
        System.out.println(user.toString());
//        save(r,"update");
        return userRepository.save(user);
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



    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        // 调用userRepository的findByusername()方法，根据参数username查询用户数据
        User result = userRepository.findUserByUsername(username);
        System.out.println(result.getId());
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出userNotFoundException异常
            throw new FoundException("No user");
        }

//        getMd5Password(user.getPassword(), salt);

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            // 是：抛出userNotFoundException异常
            throw new FoundException("user does not exist");
        }

////        String
//        // 从查询结果中获取
//        String salt = result.getSalt();
//        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
//        String md5Password = getMd5Password(password, salt);
//
//        System.out.println(md5Password);
//        System.out.println(result.getPassword());
//        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
//        if (!result.getPassword().equals(md5Password)) {
//            // 是：抛出PasswordNotMatchException异常
//            throw new FoundException("error password");
//        }

        // 创建新的user对象
//        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
//        String uuid =UUID.randomUUID().toString();
//        user.setUuid(uuid);
//        user.setUsername(result.getUsername());
//        user.setAvatar(result.getAvatar());
//        user.setModifiedTime(result.);
        // 返回新的user对象
        return result;
    }
}
