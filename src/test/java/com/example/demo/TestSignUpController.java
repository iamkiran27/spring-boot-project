package com.example.demo;


import com.example.demo.entities.UserEntity;
import com.example.demo.jpaRepo.UserRepo;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestSignUpController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testSignup() throws Exception {
        mockMvc.perform(get("/signup")).
                andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void saveUser() throws Exception {

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.ALL)
                        .param("username", "Kiran")
                        .param("password","kiran")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());


    }

    @Test
    void testUserExists() throws Exception {
        when(userRepo.findByUsername("kiran")).thenReturn(new UserEntity("kiran","kiran",1));
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.ALL)
                        .param("username", "kiran")
                        .param("password","kiran")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}




