package com.example.demo.services;

import com.example.demo.entities.ProductEntity;


import java.util.List;
import java.util.Optional;

public interface AdminServiceI {

    //save

     short saveProduct(ProductEntity productEntity);



    //get

    public List<ProductEntity> getProducts();

    //update

    public Optional<ProductEntity> updateProduct(int id);


    //delete

    public  void deleteProduct(int id);
}
