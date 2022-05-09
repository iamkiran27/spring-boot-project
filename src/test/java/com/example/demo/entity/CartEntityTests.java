package com.example.demo.entity;


import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTests {

    private CartEntity cartEntity;
    @Before
   public void before(){
         cartEntity = new CartEntity();
        cartEntity.setId(1);
        cartEntity.setQuantity(1);
        cartEntity.setTitle("Book");
        cartEntity.setImageUrl("https://image.png");
        cartEntity.setProdId(1);
        cartEntity.setPrice(20.3);


    }

    @Test
    public  void  getId()
    {
        assertEquals(1, cartEntity.getId());

assertEquals(1, cartEntity.getProdId());

    }

    @Test
    public  void  getUserEntity()
    {
        UserEntity userEntity = new UserEntity("kiran","!", 1);
        cartEntity.setUserEntity(userEntity);
assertEquals(userEntity,cartEntity.getUserEntity());
    }

    @Test
    public  void  getTitle()
    {
assertEquals("Book",cartEntity.getTitle());
    }

    @Test
    public  void  getPrice()
    {
        assertEquals(20.3,cartEntity.getPrice());

    }

    @Test
    public  void  getQuantity()
    {

        assertEquals(1,cartEntity.getQuantity());

    }
    @Test
    public  void  getImageUrl()
    {
assertEquals("https://image.png", cartEntity.getImageUrl());
    }

}
