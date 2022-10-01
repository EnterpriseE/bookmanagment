package com.book.book.service;

import com.book.book.model.Book;
import com.book.book.model.Member;
import com.book.book.model.Record;
import com.book.book.model.User;
import com.book.book.repository.RecordRepository;
import com.book.book.service.ex.FoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;


    public List<Record> findAll(){
        List<Record> lists = recordRepository.findAll();

        return lists;
    }
    public void save(User user, Member member, Book book,String action){


//        Record record = new Record();
//        if(member!=null){
//            record.setMember(member);
//        }
//        if(book!=null){
//            record.setBook(book);
//        }
//
//        if(user!=null){
//            record.setUser(user);
//        }




//        Integer count = recordRepository.findCount(record.getId());

//        record.setCreatedTime(new Date());
//        record.setAction(action);
//        recordRepository.save(record);


    }
}
