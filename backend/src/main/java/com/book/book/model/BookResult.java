package com.book.book.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

//@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookResult {

    private long id;
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
//    private List<Long> typeid = new ArrayList<>();
//    private List<String> typename = new ArrayList<>();

    private  List<TypeResult> typeResults = new ArrayList<>();
    //    外键号
//    private long type_id;
    private String uuid;
    private String name;

    private long price;
    private String brief;
    private String pic;
    private String publish;
    private String author;
    private String link;


    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public BookResult(){

    }

    public BookResult(long id, String name, long price, String brief, String pic, String author, String link, List<TypeResult> typeResults) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brief = brief;
        this.pic = pic;

        this.typeResults = typeResults;
//        this.typename.add(typename);

        this.author = author;
        this.link = link;
    }


    public BookResult(long id, String name, long price, String brief, String pic, String author, String link, long typeid , String typename, long parentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brief = brief;
        this.pic = pic;

        this.typeResults.add(new TypeResult(typeid,typename,parentId));
//        this.typename.add(typename);

        this.author = author;
        this.link = link;
    }

    public BookResult(long id, String name, long price, String brief, String pic, String author, String link) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brief = brief;
        this.pic = pic;


        this.author = author;
        this.link = link;
    }

    public List<TypeResult> getTypeResults() {
        return typeResults;
    }

    public Book ToBook(){
        Book book = new Book();
        book.setId(this.id);
        book.setName(this.name);
        book.setPrice(this.price);
        book.setBrief(this.brief);
        book.setPic(this.pic);
        book.setAuthor(this.author);
        book.setLink(this.link);

        return book;
    }


}
