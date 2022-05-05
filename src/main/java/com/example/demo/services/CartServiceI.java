package com.example.demo.services;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;

import java.util.List;

public interface CartServiceI {

    List<CartEntity> getCartItems(UserEntity userEntity);


    //get a cart item

    CartEntity getCartItem(UserEntity userEntity, int id);

    //save cart

    public  void addToCart(CartEntity cartEntity);


    //delete cart item

    public  void deleteById(int id);

}
