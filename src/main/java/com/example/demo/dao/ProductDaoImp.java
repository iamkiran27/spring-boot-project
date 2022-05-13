package com.example.demo.dao;

import com.example.demo.entities.ProductEntity;
import com.example.demo.jpaRepo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductDaoImp implements ProductDaoI{
    @Autowired
     ProductRepo productRepo;
    @Override
    public List<ProductEntity> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public ProductEntity getProductById(int id) {
        return productRepo.getById(id);
    }
}
