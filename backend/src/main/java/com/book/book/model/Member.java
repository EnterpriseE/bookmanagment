package com.book.book.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "member")
public class Member extends Base implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE)
  @Column( name = "member_id",nullable = false, updatable = false)
  private long id;
  private String username;
  private String password;
//  private long typeId;

  @Column(columnDefinition = "long default 1000")
  private long balance;
  private Date regdate;
  private String email;
//  private String idNumber;
  private String salt;

  private String uuid;

  private String avatar;

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Column(columnDefinition = "integer default 1")
  private Integer isDelete;

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  //外键
//  @ManyToOne
//  @JoinColumn(username="membertype_id", referencedColumnusername = "id")
////  @JoinColumn(username = "membertype_id", referencedColumnusername = "id")
//  private MemberType membertype;


//  @OneToMany(mappedBy = "member")
//  private List<Record> records;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public long getTypeId() {
//    return typeId;
//  }
//
//  public void setTypeId(long typeId) {
//    this.typeId = typeId;
//  }

//  public MemberType getType() {
//    return memberType;
//  }
//
//  public void setType(MemberType type) {
//    this.memberType = type;
//  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }

  public Date getRegdate() {
    return regdate;
  }

  public void setRegdate(java.sql.Date regdate) {
    this.regdate = regdate;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


//  public String getIdNumber() {
//    return idNumber;
//  }

//  public void setIdNumber(String idNumber) {
//    this.idNumber = idNumber;
//  }

  @Override
  public String toString() {
    return "Member{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
//            ", type=" + type +
            ", balance='" + balance + '\'' +
            ", regdate=" + regdate +
            ", email='" + email + '\'' +
            ", isDelete='" + isDelete + '\'' +
            '}';
  }
}
