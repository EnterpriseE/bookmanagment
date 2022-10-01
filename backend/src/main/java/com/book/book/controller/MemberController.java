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
        // 调用业务对象执行注册
        Member m = memberService.register(member);
        // 返回

        Map<String, Member> response = new HashMap<>();
        response.put("data",m);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map> login( @RequestBody  Member member,  HttpSession session) {
        // 调用业务对象的方法执行登录，并获取返回值
        Member data = memberService.login(member.getUsername(),member.getPassword());
        //登录成功后，将uid和username存入到HttpSession中
        session.setAttribute("mid", data.getId());
        session.setAttribute("username", data.getUsername());
        // System.out.println("Session中的uid=" + getUidFromSession(session));
        // System.out.println("Session中的username=" + getUsernameFromSession(session));

        // 将以上返回值和状态码OK封装到响应结果中并返回
        Map<String, Member> response = new HashMap<>();
        response.put("data",data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/change_password")
    public ResponseEntity<Map> changePassword(@RequestBody Map<String, String> payload) {
        // 调用session.getAttribute("")获取uid和username
//        System.out.println(payload);
        String uid = payload.get("uid");
//        System.out.println(uid);
        Integer id = Integer.parseInt(uid);
        String oldpass = payload.get("olspass");
        String newpass = payload.get("newpass");
//        Integer uid = getUidFromSession(session);
//        String username = getUsernameFromSession(session);

        // 调用业务对象执行修改密码

        memberService.changePassword( id, oldpass, newpass);
        // 返回成功
        Map<String, String> response = new HashMap<>();
//        response.put("data","0");
        response.put("data","success");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
