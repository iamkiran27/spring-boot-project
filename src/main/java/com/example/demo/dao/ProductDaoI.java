package com.example.demo.dao;

import com.example.demo.entities.ProductEntity;

import java.util.List;

public interface ProductDaoI {

   public List<ProductEntity> getProducts();

   public ProductEntity getProductById(int id);



}
