package com.example.demo.dao;

import com.example.demo.entities.ProductEntity;
import com.example.demo.jpaRepo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class AdminDapImp implements  AdminDaoI{

    @Autowired
     ProductRepo productRepo;
    @Override
    public void saveProduct(ProductEntity productEntity) {

        productRepo.save(productEntity);

    }

    @Override
    public List<ProductEntity> getProducts() {

        return productRepo.findAll();
    }

    @Override
    public Optional<ProductEntity> updateProduct(int id) {

      return   productRepo.findById(id);

    }

    @Override
    public void deleteProduct(int id) {

        productRepo.deleteById(id);

    }
}
