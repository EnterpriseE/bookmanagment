package com.book.book.controller;



import com.book.book.model.Book;
import com.book.book.model.User;
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
@RequestMapping("/api/users")


public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getAllUsers")
    public ResponseEntity<Map> findAllUsers()  {
//        List<MyProject> MyProjects =



        List<User> users = userService.findUndeleteUsers();
        Map<String, List> response = new HashMap<>();
//        response.put("status",1);
        response.put("data",users);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/del")
    public ResponseEntity<Map> delete(@RequestBody Map<String, Object> payload) throws Exception {
//        List<MyProject> MyProjects =

        User user1= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("user1"), User.class);
        User user2= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("user2"), User.class);

        System.out.println(user1.toString());
        System.out.println(user2.toString());

        userService.delete(user1,user2);
        Map<String, Integer> response = new HashMap<>();
        response.put("status",1);
        response.put("count",0);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Map<String, Object> payload) throws Exception {
//        List<MyProject> MyProjects =

        User user1= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("user1"), User.class);
        User user2= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("user2"), User.class);

        System.out.println(user1.toString());
        System.out.println(user2.toString());

        userService.update(user1,user2);
        Map<String, Integer> response = new HashMap<>();
        response.put("status",1);
        response.put("count",0);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @PostMapping("/register")
    public ResponseEntity<Map> register(@RequestBody User user) {
        User users = userService.register(user);
        System.out.println(users.toString());

//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        if(users==null){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "username exists");

            errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
        }else{
             Map<String, User> response = new HashMap<>();
             response.put("user",users);


            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

    }


    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody User user) {
//        List<MyProject> MyProjects =

        User users = userService.login(user);
        System.out.println(users.getId());

        if(users!=null){
            Map<String, User> response = new HashMap<>();
            response.put("user",users);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "username and password do not match");

            errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);


        }

//        return new ResponseEntity<>(MyProjects, HttpStatus.OK);
    }





}
