package com.book.book.repository;

import com.book.book.model.Record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecordRepository extends JpaRepository<Record, Long> {

//    @Query(value = "select count(?1) from record",nativeQuery = true)
//    Integer findCount(Long id);
}
