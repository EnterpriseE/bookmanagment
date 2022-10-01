package com.book.book.service;

import com.book.book.model.*;
import com.book.book.repository.BookRepository;
import com.book.book.repository.RecordRepository;
import com.book.book.service.ex.FoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.book.book.repository.TypeRepository;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional

public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TypeRepository typeRepository;


    @Autowired
    RecordRepository recordRepository;

//
//    public BookService(BookRepository bookRepository, TypeRepository typeRepository) {
//        this.bookRepository = bookRepository;
//        this.typeRepository = typeRepository;
//    }
public void save(Book user, String action){


    Record record = new Record();


    record.setSubject(user.getName());
    Date now = new Date();
    record.setCreatedTime(new Date());
    record.setDate(new Date());
    record.setAction(action);
    System.out.println(record.getCreatedTime());
    recordRepository.save(record);


}


    public List<BookResult> getAll(){
        List<BookResult> bookResults = bookRepository.findAllBy();

//        System.out.println(bookResults.size());
//        List<Book> books = new ArrayList<>();
//        for(int i = 0; i<bookResults.size();i++){
//
//            Book b = bookResults.get(i).ToBook();
//
//            books.add(bookResults.get(i).ToBook());
//        }


//        List<Book> books = bookRepository.findAllBooksAndType();
//        List<Book> books = bookRepository.findAllBy();

        return bookResults;

    }

    public List<BookResult> getAllByName(String type_name){
        return bookRepository.findBookByNameLike(type_name);
    }

    public List<Book> getAll2(){
        List<BookResult> bookResults = bookRepository.findAllBy2();
        System.out.println(bookResults.size());
        List<Book> books = new ArrayList<>();

        Optional<Type> op = typeRepository.findById((long)1);
        if(op==null ){
            throw new FoundException("type not found");
        }
        Type t = op.get();
        for(int i = 0; i<bookResults.size();i++){
            books.add(bookResults.get(i).ToBook());

            books.get(i).setType(t);
            bookRepository.save(books.get(i));
        }

//        List<Book> books = bookRepository.findAllBooksAndType();


        return null;

    }


    public List<Book> getBooksByTypeId(long typeId)  {

        List<Book> books =  bookRepository.findBooksByTypeId(typeId);

        if(books==null || books.size()==0){
            throw new FoundException("No such type");
        }

        return books;
    }


    public List<Book> getBooksByName(String name)  {

        List<Book> books =  bookRepository.findBookByName(name);

        if(books==null || books.size()==0){
            throw new FoundException("No such type");
        }

        return books;
    }

    public void addByType(BookResult bookResult)  {
//        if(book.getTypeId()==0){
//            return null;
//        }
        Book b = bookResult.ToBook();
        if(bookResult.getTypeResults()==null || bookResult.getTypeResults().size()==0){
            Optional<Type> op = typeRepository.findById((long)1);
            if(op==null ){
                throw new FoundException("type not found");
            }
            Type t = op.get();

//            b.add(bookResults.get(i).ToBook());

            b.setType(t);
            b.setIsDelete(0);
            bookRepository.save(b);
            return;
        }

//
        for (int i = 0; i<bookResult.getTypeResults().size();i++){
            System.out.println(bookResult.getTypeResults().get(i).getId());
            Optional<Type> op = typeRepository.findById(bookResult.getTypeResults().get(i).getId());
            if(op==null ){
                throw new FoundException("type not found");
            }
            Type t = op.get();
            b.getType().add(t);
            b.setIsDelete(0);
            bookRepository.save(b);
        }

//        System.out.println("t"+t.getId());


//        book.setUuid(UUID.randomUUID().toString());
//        System.out.println((book.getName()));
//        Book b = bookRepository.save(book);



//        return b;

    }
    public Book del(BookResult bookResult){

        Book book = bookResult.ToBook();
        book.setIsDelete(1);
        return bookRepository.save(book);
//        return add(book.getTypeId(),book.getName(),book.getPrice(),book.getBrief(),book.getPic(),book.getPublish(),book.getAuthor(),book.getStock(),book.getAddress());
    }
//
//    public int modify(long id, long typeId, String name, double price, String desc, String pic,
//                      String publish, String author, long stock, String address)  {
//        int count = 0;
//        try {
//            count = bookRepository.modify(id,typeId,name,price,desc,pic,publish,author,stock,address);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return count;
//    }
//    public int modify(Book book)  {
//        int count = 0;
//        try {
//            count = bookRepository.modify(book.getId(),book.getTypeId(),book.getName(),book.getPrice(),book.getDesc(),book.getPic(),book.getPublish(),book.getAuthor(),book.getStock(),book.getAddress());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return count;
//    }
//
//    public int remove(long id) throws Exception {
//        RecordDao recordDao = new RecordDao();
//        int count = 0;
//        try {
//            //1.判断id是否存在外键
//            List<Record> records = recordDao.getRecordByBookId(id);
//            if(records.size()>0){
//                throw new Exception("删除的书籍有子信息，删除失败");
//            }
//            //2.删除
//            count = bookRepository.remove(id);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return count;
//
//    }
//    public List<Book>  getByPage(int pageIndex,int pageSize) {
//        TypeDao typeDao = new TypeDao();
//        List<Book> books = null;
//        try {
//            books = bookRepository.getByPage(pageIndex,pageSize);
//            //处理type对象的数据问题
//            for(Book book:books){
//                long typeId =  book.getTypeId();
//                book.getType();//null
//                //根据typeid找到对应的type对象
//                Type type= typeDao.getById(typeId);
//                //设置给book.setType()
//                book.setType(type);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return books;
//
//    }
//    public Book getById(long id) {
//        Book book = null;
//        TypeDao typeDao = new TypeDao();
//
//        try {
//            book = bookRepository.getById(id);
//            long typeId = book.getTypeId();
//            Type type =typeDao.getById(typeId);
//            book.setType(type);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return null;
//        }
//        return book;
//    }
//
//    /**
//     * 由行数算页数
//     * @return
//     */
//    public int  getPageCount(int pageSize) {
//        int pageCount = 0;
//        try {
//            //1.获取行数
//            int rowCount = bookRepository.getCount();
//            //2.根据行数得到页数,每页多少条
//            pageCount =  (rowCount-1)/pageSize+1;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return pageCount;
//    }
//
//    public Book getByName(String bookName)  {
//        Book book = null;
//        try {
//            book = bookRepository.getByName(bookName);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return book;
//    }


}
