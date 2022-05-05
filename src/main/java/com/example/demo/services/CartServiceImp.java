package com.example.demo.services;

import com.example.demo.dao.CartDaoI;
import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartServiceImp implements  CartServiceI{

    @Autowired
    private CartDaoI cartDaoI;

    @Override
    @Transactional
    public List<CartEntity> getCartItems(UserEntity userEntity) {
        return cartDaoI.getCartItems(userEntity);
    }

    @Override
    @Transactional
    public CartEntity getCartItem(UserEntity userEntity, int id) {
        return cartDaoI.getCartItem(userEntity,id);
    }

    @Override
    @Transactional
    public void addToCart(CartEntity cartEntity) {
cartDaoI.addToCart(cartEntity);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
cartDaoI.deleteById(id);
    }
}
