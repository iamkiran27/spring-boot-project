package com.example.demo;


import com.example.demo.entities.CartEntity;
import com.example.demo.entities.ProductEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.CartRepo;
import com.example.demo.jpaRepo.UserRepo;
import com.example.demo.services.CartServiceI;
import com.example.demo.services.ProductServiceI;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
  class TestCartController {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductServiceI productServiceI;

    @MockBean
    private CartServiceI cartServiceI;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private CartRepo cartRepo;

    @Test
    @WithMockUser(username = "Kiran", password = "kiran", roles = "user")
     void getCart() throws Exception {
        UserEntity userEntity = new UserEntity("Kiran","kiran",1);

        when(userRepo.findByUsername("Kiran")).thenReturn(userEntity);
        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);
        when(cartServiceI.getCartItems(userEntity)).thenReturn(Arrays.asList(cartEntity));
        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "Kiran", password = "kiran", roles = "user")
     void postCartTest() throws Exception {
        UserEntity userEntity = new UserEntity("Kiran","kiran",1);
        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);

        when(userRepo.findByUsername("Kiran")).thenReturn(userEntity);
        when(cartServiceI.getCartItem(userEntity,1)).thenReturn(cartEntity);

        mockMvc.perform(post("/cart")
                        .contentType(MediaType.ALL)
                        .param("id", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());


    }

    @Test
    @WithMockUser(username = "Kiran", password = "kiran", roles = "user")
      void addToCart() throws Exception {
        UserEntity userEntity = new UserEntity("Kiran","kiran",1);
        when(userRepo.findByUsername("Kiran")).thenReturn(userEntity);
        when(cartServiceI.getCartItem(userEntity,1)).thenReturn(null);
        ProductEntity productEntity=new ProductEntity(1, "Book", "http:book.png", 20.5);

        when(productServiceI.getProductById(1)).thenReturn(productEntity);
        mockMvc.perform(post("/cart")
                        .contentType(MediaType.ALL)
                        .param("id", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }


    @Test
    @WithMockUser(username = "Kiran", password = "kiran", roles = "user")
      void  deleteCart() throws Exception {

        UserEntity userEntity = new UserEntity("Kiran","kiran",1);

        CartEntity cartEntity = new CartEntity(1,userEntity,1,1,"bat","http://bat.png", 30.8);

        cartServiceI.deleteById(cartEntity.getId());
//        verify(cartRepo, times(1)).deleteById(cartEntity.getId());

        mockMvc.perform(post("/cart/delete")
                        .contentType(MediaType.ALL)
                        .param("id", String.valueOf(1))
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
