package com.book.book.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")

public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "book_id",nullable = false, updatable = false)
    private long id;

//    外键号
//    private long type_id;
//    private String uuid;
    private String name;

    @Column(columnDefinition = "long default 0")
    private long price;
    private String brief;
    private String pic;
    private String publish;
    private String author;
    private String link;
    private String address;


    @Column(columnDefinition = "integer default 0")
    private Integer isDelete;

//    @OneToMany(mappedBy = "member")
//    private List<Record> records;

    @ManyToMany (targetEntity = Type.class)
    @JoinTable(
            name = "book_types",
            joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id",referencedColumnName = "type_id"))
    private List<Type> types = new ArrayList<>();

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List getType() {
        return types;
    }

    public void setType(Type type) {
        this.types.add(type);
    }

    public void removeType(Type type) {
        this.types.remove(type);
    }

//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


//    public long getTypeId() {
//        return type_id;
//    }
//
//    public void setTypeId(long typeId) {
//        this.type_id = typeId;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//
//    public long getStock() {
//        return stock;
//    }
//
//    public void setStock(long stock) {
//        this.stock = stock;
//    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
//                ", typeId=" + typeId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brief='" + brief + '\'' +
                ", pic='" + pic + '\'' +
                ", publish='" + publish + '\'' +
                ", author='" + author + '\'' +
//                ", stock=" + stock +
                ", address='" + address + '\'' +
//                ", type=" + type +
                '}';
    }
}
