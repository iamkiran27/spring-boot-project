package com.example.demo.entity;

import com.example.demo.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTests {


@Test
public  void getUsername()
{
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername("kiran");
    assertEquals("kiran", userEntity.getUsername());

}
    @Test
 public    void getPassword()
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("kiran");
        assertEquals("kiran", userEntity.getPassword());

    }
    @Test
 public    void getEnabled()
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(1);
        assertEquals(1, userEntity.getEnabled());

    }



}

