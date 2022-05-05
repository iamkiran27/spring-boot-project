package com.example.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @ManyToOne(cascade = {
            CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name="user", nullable=false)
    private UserEntity userEntity;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "prod_id")
    private int prodId;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private  String imageUrl;

    @Column(name = "price")
    private Double price;


}
