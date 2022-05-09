package com.example.demo.controllers;


import com.example.demo.entities.ProductEntity;
import com.example.demo.services.ProductServiceI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestController {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductServiceI productServiceI;

    @Test
    public  void test() throws Exception {
        ProductEntity productEntity=new ProductEntity(1, "Book", "http:book.png", 20.5);
        ProductEntity productEntity2=new ProductEntity(1, "B", "http:book.png", 20.5);

        when(productServiceI.getProducts()).thenReturn(Arrays.asList(productEntity,productEntity2));
        this.mockMvc.perform( get("/"))
                .andExpect(status().isOk());

    }
}
