package com.dyyhub.controller;

import com.dyyhub.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String test(){
        System.out.println("test() is running....");
        return "springboot";
    }

    @GetMapping("/{id}")
    public User test(@PathVariable Integer id){

        User user = new User(id,"Tom",18);
        return user;
    }
}
