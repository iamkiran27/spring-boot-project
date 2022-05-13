package com.example.demo;


import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.UserRepo;
import com.example.demo.services.CartServiceI;
import com.example.demo.services.ProductServiceI;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
 class TestController {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductServiceI productServiceI;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private CartServiceI cartServiceI;

    @Test
    @WithMockUser(username = "Kiran", password = "kiran", roles = "user")
      void test() throws Exception {


        ProductEntity productEntity=new ProductEntity(1, "Book", "http:book.png", 20.5);
        ProductEntity productEntity2=new ProductEntity(1, "B", "http:book.png", 20.5);

        UserEntity userEntity = new UserEntity("Kiran","kiran",1);
        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        when(productServiceI.getProducts()).thenReturn(Arrays.asList(productEntity,productEntity2));
        when(userRepo.findByUsername("Kiran")).thenReturn(new UserEntity("Kiran", "kiran", 1));
        when(cartServiceI.getCartItems(userEntity)).thenReturn(Arrays.asList(cartEntity));

        mockMvc.perform(get("/")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())

                )
                .andDo(print())
                .andExpect(status().isOk());

    }
}
