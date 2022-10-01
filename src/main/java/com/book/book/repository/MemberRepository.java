package com.book.book.repository;

import com.book.book.model.Book;
import com.book.book.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByUsername(String name);

    Member findMemberById(Integer id);


    @Query("Select m from Member m where m.isDelete = 0")
    List<Member> findUndeleteUsers();

}
