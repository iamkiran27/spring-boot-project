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
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class CartController {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private ProductServiceI productServiceI;

    @Autowired
    private CartServiceI cartServiceI;

    @GetMapping("/cart")
    public  String get(HttpServletRequest httpServletRequest, Model model)
    {

double total  = 0;
int items= 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity2 = userRepo.findByUsername(username);

        List<CartEntity> cartEntityList =  cartServiceI.getCartItems(userEntity2);

        for(CartEntity cartEntity : cartEntityList)
        {
            total += cartEntity.getQuantity() * cartEntity.getPrice();
            items += cartEntity.getQuantity();
        }
        model.addAttribute("items",items );
model.addAttribute("products", cartEntityList);
        model.addAttribute("total", total);
        return  "cart";
    }

    @PostMapping("/cart")
    public String post(HttpServletRequest httpServletRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String id = httpServletRequest.getParameter("id");
        UserEntity userEntity2 = userRepo.findByUsername(username);

        CartEntity cartEntity2 = cartServiceI.getCartItem(userEntity2, Integer.parseInt(id));

        if(cartEntity2 == null)
        {
            ProductEntity productEntity = productServiceI.getProductById(Integer.parseInt(id));

            CartEntity cartEntity = new CartEntity();
            UserEntity userEntity = userRepo.findByUsername(username);
            cartEntity.setUserEntity(userEntity);
            cartEntity.setProdId(productEntity.getId());
            cartEntity.setPrice(productEntity.getPrice());
            cartEntity.setQuantity(1);
            cartEntity.setTitle(productEntity.getTitle());
            cartEntity.setImageUrl(productEntity.getImageUrl());

            cartServiceI.addToCart(cartEntity);
        }
        else {
            cartEntity2.setQuantity(cartEntity2.getQuantity() + 1);
            cartServiceI.addToCart(cartEntity2);
        }




        return "redirect:/";
    }


    @PostMapping("/cart/delete")
    public  String delete(HttpServletRequest httpServletRequest)
    {
     String id =    httpServletRequest.getParameter("id");
     cartServiceI.deleteById(Integer.parseInt(id));

        return "redirect:/cart";
    }
}
