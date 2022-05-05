package com.example.demo.services;


import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.CartRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCartServices {

    @Autowired
    private CartServiceI cartServiceI;

    @MockBean
    private CartRepo cartRepo;

    @Test
    public void getCartItems( ){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);
        when(cartRepo.findByUsername(userEntity)).thenReturn(Stream.of(new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8)).collect(Collectors.toList()));
        assertEquals(1, cartServiceI.getCartItems(userEntity).size());
    }


    //get a cart item

    @Test
    public void  getCartItem(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
when(cartRepo.findByUsernameAndId(userEntity, 1)).thenReturn(cartEntity);

assertEquals(cartEntity, cartServiceI.getCartItem(userEntity, 1));
    };

    //save cart

    @Test
    public  void addToCart(){
        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        cartServiceI.addToCart(cartEntity);
        verify(cartRepo, times(1)).save(cartEntity);
    }


    //delete cart item
@Test
    public  void deleteById()
    {

        UserEntity userEntity = new UserEntity("kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
cartServiceI.deleteById(cartEntity.getId());
verify(cartRepo, times(1)).deleteById(cartEntity.getId());


    }

}
