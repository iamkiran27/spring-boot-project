package com.example.demo.jpaRepo;

import com.example.demo.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
}
