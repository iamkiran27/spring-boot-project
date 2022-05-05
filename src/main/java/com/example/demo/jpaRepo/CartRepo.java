package com.example.demo.jpaRepo;

import com.example.demo.entities.CartEntity;
import com.example.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepo extends JpaRepository<CartEntity, Integer> {




    @Query("select u from CartEntity u where u.userEntity= ?1 and u.prodId= ?2")
    CartEntity findByUsernameAndId(UserEntity userEntity, int prod_id);


    @Query("select u from CartEntity u where u.userEntity= ?1")
    List<CartEntity> findByUsername(UserEntity userEntity);

}
