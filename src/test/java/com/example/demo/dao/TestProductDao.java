package com.example.demo.dao;


import com.example.demo.entities.ProductEntity;
import com.example.demo.jpaRepo.ProductRepo;
import org.junit.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductDao {

    @Autowired
    private ProductDaoI productDaoI;

    @MockBean
    private ProductRepo productRepo;

    @Test
    public void getProducts(){
        when(productRepo.findAll()).thenReturn(Stream.of(new ProductEntity(1,"book","http://image.png",20.6),new ProductEntity(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2, productDaoI.getProducts().size());


    }

    @Test
    public void getProductById(){
        ProductEntity productEntity = new ProductEntity(1,"book", "http://book.png", 60.9);
        when(productRepo.getById(productEntity.getId())).thenReturn(productEntity);
        assertEquals(productEntity, productDaoI.getProductById(productEntity.getId()));
    }
}
