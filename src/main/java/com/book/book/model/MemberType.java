package com.book.book.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MemberType")

public class MemberType implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false, updatable = false)
  private long id;
  private String name;
  private long amount;
  private double discount;
  private long recharge;
  private long keepDay;

//  @OneToMany(mappedBy="membertype")
//  private List<Member> members;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }


  public long getRecharge() {
    return recharge;
  }

  public void setRecharge(long recharge) {
    this.recharge = recharge;
  }


  public long getKeepDay() {
    return keepDay;
  }

  public void setKeepDay(long keepDay) {
    this.keepDay = keepDay;
  }

  @Override
  public String toString() {
    return "MemberType{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", amount='" + amount + '\'' +
            ", discount=" + discount +
            ", recharge=" + recharge +
            ", keepDay=" + keepDay +
            '}';
  }
}
