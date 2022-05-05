package com.example.demo.services;

import com.example.demo.entities.ProductEntity;

import java.util.List;

public interface ProductServiceI {

    public List<ProductEntity> getProducts();

    public ProductEntity getProductById(int id);
}
