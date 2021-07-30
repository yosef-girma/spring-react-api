package com.example.springreactcrud.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping("/")
    public String home() {
        return "Home";

    }

    @RequestMapping("/user")
    public String user() {
        return "User";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "Admin";
    }


}
