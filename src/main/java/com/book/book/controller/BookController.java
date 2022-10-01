package com.book.book.controller;


import com.book.book.controller.ex.ParamException;
import com.book.book.model.Book;
import com.book.book.model.BookResult;
import com.book.book.model.Type;
import com.book.book.model.User;
import com.book.book.service.BookService;
import com.book.book.service.RecordService;
import com.book.book.service.TypeService;
import com.book.book.util.JsonXMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RecordService recordService;

    @PostMapping("/uploadPic")
    public ResponseEntity<?> uploadPic(@RequestParam("file")MultipartFile file) throws IOException {

        String filename= file.getOriginalFilename();
        System.out.println(filename);
        String path = "D:\\VScodeWorkspace\\onlinelib\\onlinelib\\public\\Images\\"+filename;
        file.transferTo(new File(path));

        String path2 = "D:\\VScodeWorkspace\\book\\public\\file\\"+filename;
        file.transferTo(new File(path2));

        return ResponseEntity.ok("susscess upload");
    }


    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {


        String filename= file.getOriginalFilename();
        System.out.println(filename);
        String path = "D:\\VScodeWorkspace\\onlinelib\\onlinelib\\public\\file\\"+filename;
        file.transferTo(new File(path));

        String path2 = "D:\\VScodeWorkspace\\book\\public\\file\\"+filename;
        file.transferTo(new File(path2));

        return ResponseEntity.ok("susscess upload");
    }



    @GetMapping("/getAll")
    public ResponseEntity<Map> getAll() {
//        List<MyProject> MyProjects =
        int pageNumber = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BookResult> types = bookService.getAll();
        System.out.println(types.size());
        Map<String, List<BookResult>> response = new HashMap<>();
        response.put("data", types);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @GetMapping("/getAllByName/{type_name}")
    public ResponseEntity<Map> getAllByName(@PathVariable String type_name) {
//        List<MyProject> MyProjects =
//        int pageNumber = 1;
//        int pageSize = 5;
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BookResult> types = bookService.getAllByName(type_name);
        System.out.println(types.size());
        Map<String, List<BookResult>> response = new HashMap<>();
        response.put("data", types);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/getBooksByTypeId")
    public ResponseEntity<Map> getBooksByTypeId(@RequestBody Map<String, Integer> payload) throws Exception {

//        System.out.println(payload.get("F").toString());
//             Book book= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("book"),Book.class);
//        Type type=JsonXMLUtil.map2obj((Map<String, Object>)payload.get("type"),Type.class);

        Integer typeid = payload.get("id");
//        System.out.println(book.getName());

        if (payload==null){
            throw new ParamException("err in type");
        }

        List<Book> books = bookService.getBooksByTypeId(typeid);

        Map<String, List<Book>> response = new HashMap<>();

        response.put("books",books);

//        response.put("status",1);
//        response.put("count",0);
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }



    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody BookResult bookResult) throws Exception {


        System.out.println(bookResult.getName());
//        System.out.println(bookResult.getTypeResults().get(0).getType_name());
        bookService.addByType(bookResult);

//        recordService.save(book);

        Map<String, Integer> response = new HashMap<>();

        response.put("status",1);
        response.put("count",0);
//        response.put("book",b)
        return new ResponseEntity<>(response, HttpStatus.CREATED);



    }


    @PostMapping("/del")
    public ResponseEntity<Map> del(@RequestBody BookResult bookResult)  {


        bookService.del(bookResult);

        Map<String, Integer> response = new HashMap<>();

        response.put("status",1);
        response.put("count",0);
//        response.put("book",b)
        return new ResponseEntity<>(response, HttpStatus.CREATED);



    }


//    @PostMapping("/insert")
//    public ResponseEntity<Map> add(@RequestBody Map<String, Object> payload) throws Exception {
//
////        System.out.println(payload.get("F").toString());
//        Book book= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("book"),Book.class);
//        Type type=JsonXMLUtil.map2obj((Map<String, Object>)payload.get("type"),Type.class);
//
//        System.out.println(book.getName());
//
//        if (type==null || type.getId()==0){
//            throw new ParamException("err in type");
//        }
//        Long typeid = type.getId();
//        System.out.println();
//        bookService.addByType(book,type.getId());
//
////        recordService.save(book);
//
//        Map<String, Integer> response = new HashMap<>();
//
//        response.put("status",1);
//        response.put("count",0);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//
//
//
//    }

}
