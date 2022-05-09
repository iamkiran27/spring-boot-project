package com.example.demo.controller;


import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/signup")
    public  String get(UserEntity user, Model model){

        model.addAttribute("user", new UserEntity());
        return "signup";
    }


    @PostMapping("/signup")
    public  String post(@Valid  @ModelAttribute("user") UserEntity user, Errors errors, Model model) throws  NullPointerException

    {
        if (null != errors && errors.getErrorCount() > 0) {
            return "signup";
        } else {
            UserEntity account = userRepo.findByUsername(user.getUsername());

            if(account == null)
            {
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(user.getUsername());
                userEntity.setPassword(user.getPassword());
                userEntity.setEnabled(1);
                userRepo.save(userEntity);
                return "redirect:/signin";
            }
            else {

                errors.rejectValue("username","error","An account with this username already exists.");
                return  "signup";
            }


        }

    }
}
