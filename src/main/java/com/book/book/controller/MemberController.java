package com.book.book.controller;

import com.book.book.model.Book;
import com.book.book.model.Member;
import com.book.book.model.User;
import com.book.book.service.MemberService;
import com.book.book.util.JsonXMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("*")
@RestController()
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    MemberService memberService;



    @PostMapping("/updateMember")
    public ResponseEntity<Map> updateMember(@RequestBody Map<String, Object> payload) throws Exception{

        System.out.println(payload.toString());
        User user= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("user"), User.class);
        Member member= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("member"), Member.class);

        System.out.println(user);

        System.out.println(member);
        Member m = memberService.update(user,member);
        Map<String, Member> response = new HashMap<>();
//        response.put("status",1);
        response.put("data",m);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/del")
    public ResponseEntity<Map> delete(@RequestBody Map<String, Object> payload) throws Exception{

        System.out.println(payload.toString());
        User user= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("user"), User.class);
        Member member= JsonXMLUtil.map2obj((Map<String, Object>)payload.get("member"), Member.class);

        System.out.println(user);

        System.out.println(member);
        Member m = memberService.delete(user,member);
        Map<String, Member> response = new HashMap<>();
//        response.put("status",1);
        response.put("data",m);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/allMembers")
    public ResponseEntity<Map> findAllMembers()  {
//        List<MyProject> MyProjects =

        System.out.println("all memebers");

        List<Member> members = memberService.findUndeleteUsers();
        Map<String, List> response = new HashMap<>();
//        response.put("status",1);
        response.put("data",members);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/register")
    public ResponseEntity<Map> register(@RequestBody Member member) {
        // ??????????????????????????????
        Member m = memberService.register(member);
        // ??????

        Map<String, Member> response = new HashMap<>();
        response.put("data",m);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map> login( @RequestBody  Member member,  HttpSession session) {
        // ????????????????????????????????????????????????????????????
        Member data = memberService.login(member.getUsername(),member.getPassword());
        //?????????????????????uid???username?????????HttpSession???
        session.setAttribute("mid", data.getId());
        session.setAttribute("username", data.getUsername());
        // System.out.println("Session??????uid=" + getUidFromSession(session));
        // System.out.println("Session??????username=" + getUsernameFromSession(session));

        // ??????????????????????????????OK?????????????????????????????????
        Map<String, Member> response = new HashMap<>();
        response.put("data",data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/change_password")
    public ResponseEntity<Map> changePassword(@RequestBody Map<String, String> payload) {
        // ??????session.getAttribute("")??????uid???username
//        System.out.println(payload);
        String uid = payload.get("uid");
//        System.out.println(uid);
        Integer id = Integer.parseInt(uid);
        String oldpass = payload.get("olspass");
        String newpass = payload.get("newpass");
//        Integer uid = getUidFromSession(session);
//        String username = getUsernameFromSession(session);

        // ????????????????????????????????????

        memberService.changePassword( id, oldpass, newpass);
        // ????????????
        Map<String, String> response = new HashMap<>();
//        response.put("data","0");
        response.put("data","success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
