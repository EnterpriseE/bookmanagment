package com.book.book.repository;

import com.book.book.model.Book;
import com.book.book.model.BookResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select new com.book.book.model.BookResult(b. id, b.name, b.price, b.brief, b.pic, b.author, b.link,t.id,t.type_name,t.parentId)  from Book b join b.types t where b.isDelete=0  ")

//    @Query("select b from Book b left join fetch b.types t")
    List<BookResult> findAllBy();

    @Query("Select new com.book.book.model.BookResult(b. id, b.name, b.price, b.brief, b.pic, b.author, b.link)  from Book b ")

    List<BookResult> findAllBy2();

    @Query("Select b from Book b join b.types t")
    List<Book> findAllBooksAndType();


    @Query("Select b from Book b join b.types t where t.id = :type_id")
    List<Book> findBooksByTypeId(@Param("type_id") Long type_id);


    @Query(value = "select count(?1) from book",nativeQuery = true)
    Integer getPages(Long id);



    List<Book> findBookByName(String name);



    List<Book> findBookByNameContaining(String name);

    @Query("Select new com.book.book.model.BookResult(b. id, b.name, b.price, b.brief, b.pic, b.author, b.link,t.id,t.type_name,t.parentId)  from Book b join b.types t where b.name like CONCAT('%',:name,'%') and b.isDelete=0  ")

    List<BookResult>  findBookByNameLike(@Param("name") String name);

//
//    @Query("FROM Book b where b.type_id = :id")
//    List<Book> findAllByTypeId(Long id) throws SQLException;
}
