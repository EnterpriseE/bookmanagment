package com.book.book.service;

import com.book.book.model.Member;
import com.book.book.model.Record;
import com.book.book.model.Type;
import com.book.book.model.TypeResult;
import com.book.book.repository.RecordRepository;
import com.book.book.repository.TypeRepository;
import com.book.book.service.ex.FoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeService {
    @Autowired
    TypeRepository typeRepository;


    @Autowired
    RecordRepository recordRepository;

//    public TypeService(TypeRepository typeRepository) {
//        this.typeRepository = typeRepository;
//    }

    public void save(Type type, String action){


       Record record = new Record();


        record.setSubject(type.getType_name());
        record.setCreatedTime(new Date());
        record.setAction(action);
        record.setDate(new Date());
        recordRepository.save(record);


    }



    public List<Type> getAll(){
        List<TypeResult> typeResults = typeRepository.findAllBy();
        List<Type> types = new ArrayList<>();
        for(int i = 0; i<typeResults.size();i++){
            types.add(typeResults.get(i).toType());
        }


        return types;

    }
    public Integer add(Type type){

        if(getById(type.getParentId())==0 && type.getId()!=0){
            new FoundException("does not find parent");
        }
        if(findAllByName(type.getType_name())!=0 ){
            new FoundException("name exist");
        }


        save(type,"add");
        System.out.println(type.getType_name());
        System.out.println(type.getParentId());
//        typeRepository.add(type.getName(),type.getParentId());

        typeRepository.save(type);
        return 1;

    }


    public int modify(Type type){
        int count =0;

        long id = type.getId();
        String type_name = type.getType_name();
        long parentId = type.getParentId();

//        int count = 0;
        if(getById(type.getParentId())==0 && type.getId()!=0){
            new FoundException("does not find parent");
        }
        if(findAllByName(type.getType_name())!=0 ){
            new FoundException("name exist");
        }


        try{
            count  = typeRepository.modify(id,type_name,parentId);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        save(type,"modify");
        return count;

//        return count;
    }

    /**
     * 删除:
     * 表与表之间关系：逻辑关系
     * type是book的一个外键
     * @param
     * @return 0:删除失败  >0:  Exception:提示用户的信息
//     */
    public int delete(Type type) {
        //如果有子项，是不能删除

//        type.set
        typeRepository.save(type);
        return 1;


    }



    public Integer getById(long id){
//        typeRepository.findTypeBy
        return typeRepository.findAllById(id).size();
    }

    public Integer findAllByName(String type_name){
//        typeRepository.findTypeBy

        return typeRepository.findAllByName(type_name).size();
    }
}
