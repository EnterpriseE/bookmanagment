package com.book.book.repository;

import com.book.book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where username = ?1 and password =?2",nativeQuery = true)
    User findUserLogin(String username,String password);

//    @Query(value = "select * from my_project where username = ?1",nativeQuery = true)
//    List<User> findUser(Integer category);

//    @Query(value = "select * from user where username = ?1",nativeQuery = true)
//    List<User> findUserByUsername(String username);

    User findUserByUsername(String username);


    @Query("Select u from User u where u.isDelete = 0")
    List<User> findUndeleteUsers();

}
