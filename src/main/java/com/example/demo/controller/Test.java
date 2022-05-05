package com.example.demo.controller;


import com.example.demo.entities.ProductEntity;

import com.example.demo.services.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Test {

    @Autowired
    private ProductServiceI productServiceI;

    @GetMapping("/")
    public  String test(Model model, HttpServletRequest httpServletRequest)
    {
      List<ProductEntity> productEntityList =   productServiceI.getProducts();

        model.addAttribute("products", productEntityList);

        if(httpServletRequest.isUserInRole("ROLE_admin"))
        {
            return "redirect:/admin";
        }
        else {
            return "home";
        }

    }
}
