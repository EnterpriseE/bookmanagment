package com.book.book.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书类型的Javabean
 */
@Entity
@Table(name="type")
public class Type implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE)
  @Column(name = "type_id",nullable = false, updatable = false)
  private long id;
//  private String uuid;
  private String type_name;

  @Column(columnDefinition = "long default 0")
  private long parentId;

//  @OneToMany(mappedBy = "type")
//  private List<Record> records;

  @ManyToMany(mappedBy = "types")
  private List<Book> books  = new ArrayList<>();



//  public List<Record> getRecords() {
//    return records;
//  }

  public void setBooks(Book book) {
    this.books.add(book);
  }

  public void removeBooks(Book book) {
    this.books.remove(book);
  }

  public List<Book> getBooks() {
    return books;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getType_name() {
    return type_name;
  }

  public void setType_name(String name) {
    this.type_name = name;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

  @Override
  public String toString() {
    return "Type{" +
            "id=" + id +
            ", name='" + type_name + '\'' +
            ", parentId=" + parentId +
            '}';
  }
}
