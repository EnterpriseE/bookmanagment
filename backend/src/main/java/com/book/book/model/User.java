package com.book.book.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "user_id",nullable = false, updatable = false)
    private Long user_id;
    private String uuid;
    private String imageUrl;
    private String salt;


    @Column(name = "username",nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private String brief;
    private String email;

    @Column(columnDefinition = "integer default 3")
    private Integer rk;


    @Column(columnDefinition = "integer default 1")
    private Integer isDelete;


//    @OneToMany(mappedBy = "user")
//    private List<Record> records;


    public User() {}


    public User(Long id, String user_uuid, String email, String imageUrl, String username, String password,String brief) {
        this.user_id = user_id;
        this.uuid = uuid;
        this.imageUrl = imageUrl;
        this.username = username;
        this.password = password;
        this.email = email;
//        this.brief = brief;

    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getrk() {
        return rk;
    }

    public void setrk(Integer rk) {
        this.rk = rk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
