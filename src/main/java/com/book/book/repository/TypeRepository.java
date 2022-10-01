package com.book.book.repository;

import com.book.book.model.Type;
import com.book.book.model.TypeResult;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
@Component
public interface TypeRepository extends JpaRepository<Type, Long> {


    @Query("Select new com.book.book.model.TypeResult(t.id, t.type_name,t.parentId)  from Type t ")
    List<TypeResult> findAllBy();


    @Query("Select new com.book.book.model.TypeResult(t.id, t.type_name,t.parentId)  from Type t where t.id = :type_id")
    List<TypeResult> findAllById( @Param("type_id" )Long type_id);



    @Query("Select new com.book.book.model.TypeResult(t.id, t.type_name,t.parentId)  from Type t where t.type_name = :type_name ")
    List<TypeResult> findAllByName(@Param("type_name" )String type_name);

    @Modifying
    @Query(value = "insert into type values(null,?1,?2)", nativeQuery = true)
    Integer add(String name,long parentId) ;

//    List<Type> findAllById(Long id) throws SQLException;

    @Modifying(clearAutomatically = true)
    @Query(value = "update Type t set t.type_name=?2,t.parent_Id=?3 where t.type_id =?1", nativeQuery = true)
    int modify(long id, String type_name,long parentId) throws SQLException;


//    Type findById();


//        int save(Ty);
//    @Query(value = "delete from type where id=?1",nativeQuery = true)
    void deleteAllById(long id);

}
