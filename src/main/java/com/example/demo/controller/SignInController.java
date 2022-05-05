package com.example.demo.controller;



import com.example.demo.jpaRepo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class SignInController {


    @Autowired
    private UserRepo userRepo;

    @GetMapping("/signin")
    public  String get(Model model)
    {



        return  "signin";
    }




}
