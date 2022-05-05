package com.example.demo.dao;

import com.example.demo.entities.ProductEntity;
import com.example.demo.jpaRepo.ProductRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdminDao {

    @Autowired
    private AdminDaoI adminDaoI;

    @MockBean
    private ProductRepo productRepo;

    @Test
    public void testSaveProduct( ){
        ProductEntity productEntity=new ProductEntity(1, "Book", "http:book.png", 20.5);
        adminDaoI.saveProduct(productEntity);
        verify(productRepo,times(1)).save(productEntity);



    }

    //get

    @Test
    public void testGetProducts(){
        when(productRepo.findAll()).thenReturn(Stream.of(new ProductEntity(1,"book","http://image.png",20.6),new ProductEntity(2,"bat","http://bat.png",40.0)).collect(Collectors.toList()));
        assertEquals(2,adminDaoI.getProducts().size());
    }

    //update

    @Test
    public void testUpdateProduct(){
        ProductEntity productEntity=new ProductEntity(3, "Book", "http:book.png", 20.5);

        Optional<ProductEntity> optionalProductEntity = Optional.of(productEntity);
        when(productRepo.findById(3)).thenReturn(optionalProductEntity);

        Optional<ProductEntity> productEntity1 =  adminDaoI.updateProduct(productEntity.getId());
        ProductEntity product = new ProductEntity();
        if(productEntity1.isPresent())
        {
            product = productEntity1.get();
        }

        assertEquals(product , productEntity);




    }


    //delete

    @Test
    public  void testDeleteProduct(){

        ProductEntity productEntity=new ProductEntity(1, "Book", "http://book.png", 34.5);
        adminDaoI.deleteProduct(productEntity.getId());
        verify(productRepo,times(1)).deleteById(productEntity.getId());

    }


}
