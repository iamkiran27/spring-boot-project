package com.example.demo.dao;

import com.example.demo.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface AdminDaoI {

    //save

    public void saveProduct(ProductEntity productEntity);

    //get

    public List<ProductEntity> getProducts();

    //update

    public Optional<ProductEntity> updateProduct(int id);


    //delete

    public  void deleteProduct(int id);

}
