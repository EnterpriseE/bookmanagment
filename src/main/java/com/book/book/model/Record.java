package com.book.book.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="record")

public class Record extends Base implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE)
  @Column(name = "id",nullable = false, updatable = false)
  private long id;
  private String action;
  private String subject;
  private Date date;
//  private String origin;
//  private String object;
//
//  public void setObject(String object) {
//    this.object = object;
//  }
//
//  public String getObject() {
//    return object;
//  }


//  public String getOrigin() {
//    return origin;
//  }
//
//  public void setOrigin(String origin) {
//    this.origin = origin;
//  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }


  @Override
  public String toString() {
    return "Record{" +
            "id=" + id +
            ", action=" + action +
            ", subject=" + subject +

            '}';
  }
}
