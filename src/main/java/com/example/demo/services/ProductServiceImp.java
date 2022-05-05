package com.example.demo.services;

import com.example.demo.dao.ProductDaoI;
import com.example.demo.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImp implements  ProductServiceI{

    @Autowired
    private ProductDaoI productDaoI;

    @Override
    @Transactional
    public List<ProductEntity> getProducts() {
        return productDaoI.getProducts();
    }

    @Override
    @Transactional
    public ProductEntity getProductById(int id) {
        return productDaoI.getProductById(id);
    }
}
