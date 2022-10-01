package com.book.book.controller;

import com.book.book.model.Type;
import com.book.book.model.User;
import com.book.book.service.TypeService;
import com.book.book.service.UserService;
import com.book.book.util.JsonXMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/types")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping("/getAll")
    public ResponseEntity<Map> getAll() {
//        List<MyProject> MyProjects =

        List<Type> types = typeService.getAll();
        Map<String, List<Type>> response = new HashMap<>();
//        System.out.println(types.get(0).);
        response.put("data", types);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/modify")
    public ResponseEntity<Map>  modify(@RequestBody Map<String, Object> payload) throws Exception {
//        List<MyProject> MyProjects =

//        List<Type> type = typeService.getAll();
        Type type= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("type"), Type.class);

        System.out.println(type);
        int count = typeService.modify(type);

        Map<String, Integer> response = new HashMap<>();
        response.put("status",0);
        response.put("type",count);
        return new ResponseEntity<>(response, HttpStatus.CREATED);



    }



    @PostMapping("/del")
    public ResponseEntity<Map> delete(@RequestBody Type type) {
//        List<MyProject> MyProjects =

//        List<Type> type = typeService.getAll();
        System.out.println(type);
//        Type t = typeService.delete(type);
        Map<String, Type> response = new HashMap<>();
//        if (count==0){
//            response.put("status",0);
//            response.put("count",0);
//
//        }else{
//            response.put("status",1);
//            response.put("count",count);
//        }
//        response.put("data", t);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/insert")
    public ResponseEntity<Map> add(@RequestBody Type type) {
//        List<MyProject> MyProjects =

//        List<Type> type = typeService.getAll();
        System.out.println(type);
        typeService.add(type);
        Map<String, Integer> response = new HashMap<>();
//        if (count==0){
//            response.put("status",0);
//            response.put("count",0);
//
//        }else{
//            response.put("status",1);
//            response.put("count",count);
//        }
        response.put("data", 1);
//        response.put("type", type);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }



//    @gettMapping("/{id}")
//    public ResponseEntity<Map> add(@) {
////        List<MyProject> MyProjects =
//
////        List<Type> type = typeService.getAll();
//        System.out.println(type);
//        int count = typeService.add(type);
//        Map<String, Integer> response = new HashMap<>();
//        if (count==0){
//            response.put("status",0);
//            response.put("count",0);
//
//        }else{
//            response.put("status",1);
//            response.put("count",count);
//        }
////        response.put("type", type);
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//
//    }



}
