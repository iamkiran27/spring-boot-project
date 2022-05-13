package com.example.demo.controller;


import com.example.demo.entities.ProductEntity;

import com.example.demo.services.AdminServiceI;
import com.example.demo.services.ProductServiceI;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {



    @Autowired
    private ProductServiceI productServiceI;

    @Autowired
    private AdminServiceI adminServiceI;


    @GetMapping("/admin/add-product")
    public String get(Model model)
    {

        model.addAttribute("product", new ProductEntity());
        return "addproduct";
    }


    @PostMapping("/admin/add-product")
    public String post(@Valid @ModelAttribute("product") ProductEntity productEntity, @RequestParam("file") MultipartFile file ,Errors errors, Model model) throws IOException {

if(errors.hasFieldErrors() )
{
    return  "addproduct";
}

        if(file.isEmpty())
        {

            errors.rejectValue("imageUrl","error","Upload an image.");
            return  "addproduct";
        }

        byte[] image = Base64.encodeBase64(file.getBytes());
        String result = new String(image);
String res = "data:image/png;base64," + result;
        productEntity.setImageUrl(res);

        adminServiceI.saveProduct(productEntity);
return  "redirect:/admin";
    }

    @GetMapping("/admin")
    public  String getProd(Model model)
    {

        List<ProductEntity> productEntityList =   adminServiceI.getProducts();

        model.addAttribute("products", productEntityList);
        return  "admin-products";
    }


    @GetMapping("/admin/update-product")
            public String update(@RequestParam("prodId") int prodId, Model model)

    {
        Optional<ProductEntity> productEntity = adminServiceI.updateProduct(prodId);
        ProductEntity productEntity1 = new ProductEntity();
        if(productEntity.isPresent())
        {
            productEntity1 = productEntity.get();
        }

        model.addAttribute("product", productEntity1);

        return "addproduct";

    }

    @GetMapping("/admin/delete-product")
    public  String delete(@RequestParam("prodId") int prodId)
    {
        adminServiceI.deleteProduct(prodId);

        return "redirect:/admin";
    }

}
