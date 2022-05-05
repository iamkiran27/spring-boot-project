package com.example.demo.services;


import com.example.demo.dao.AdminDaoI;
import com.example.demo.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminServiceI {


    @Autowired
    private AdminDaoI adminDaoI;


    @Override
    @Transactional
    public void saveProduct(ProductEntity productEntity) {

        adminDaoI.saveProduct(productEntity);



    }

    @Override
    @Transactional
    public List<ProductEntity> getProducts() {


        return adminDaoI.getProducts();
    }

    @Override
    @Transactional
    public Optional<ProductEntity> updateProduct(int id) {

      return   adminDaoI.updateProduct(id);

    }

    @Override
    @Transactional
    public void deleteProduct(int id) {

        adminDaoI.deleteProduct(id);

    }
}
