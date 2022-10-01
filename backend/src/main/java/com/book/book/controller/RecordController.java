package com.book.book.controller;

import com.book.book.model.Record;
import com.book.book.model.User;
import com.book.book.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    RecordService recordService;

    @GetMapping("/getAllRecords")
    public ResponseEntity<Map> findAllRecords()  {
//        List<MyProject> MyProjects =



        List<Record> records = recordService.findAll();
        Map<String, List> response = new HashMap<>();
//        response.put("status",1);
        response.put("data",records);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
