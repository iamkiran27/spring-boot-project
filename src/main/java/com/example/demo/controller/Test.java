package com.example.demo.controller;


import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ProductEntity;

import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.UserRepo;
import com.example.demo.services.CartServiceI;
import com.example.demo.services.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Test {

    @Autowired
    private ProductServiceI productServiceI;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartServiceI cartServiceI;

    @GetMapping("/")
    public  String test(Model model, HttpServletRequest httpServletRequest)
    {
      List<ProductEntity> productEntityList =   productServiceI.getProducts();
        int items= 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity2 = userRepo.findByUsername(username);

        List<CartEntity> cartEntityList =  cartServiceI.getCartItems(userEntity2);

        for(CartEntity cartEntity : cartEntityList)
        {

            items += cartEntity.getQuantity();
        }
        model.addAttribute("items",items );

        model.addAttribute("products", productEntityList);

        if(httpServletRequest.isUserInRole("ROLE_admin"))
        {
            return "redirect:/admin";
        }
        else {
            return "home";
        }

    }
    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }
}
