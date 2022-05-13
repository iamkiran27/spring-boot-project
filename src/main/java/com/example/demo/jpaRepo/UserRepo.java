package com.example.demo.jpaRepo;

import com.example.demo.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



public interface UserRepo extends JpaRepository<UserEntity, String> {

    @Query("select u from UserEntity u where u.username = ?1")
    UserEntity findByUsername(String username);

}
