package com.example.demo.dao;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CartDaoImp implements CartDaoI{

    @Autowired
     CartRepo cartRepo;
    @Override
    public List<CartEntity> getCartItems(UserEntity userEntity) {
        return cartRepo.findByUsername(userEntity);
    }

    @Override
    public CartEntity getCartItem(UserEntity userEntity, int id) {
        return cartRepo.findByUsernameAndId(userEntity,id);
    }

    @Override
    public void addToCart(CartEntity cartEntity) {
cartRepo.save(cartEntity);
    }

    @Override
    public void deleteById(int id) {

        cartRepo.deleteById(id);

    }
}
