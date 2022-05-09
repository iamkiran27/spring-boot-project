package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SpringBootWebAppApplicationTests {


    @Test
    void main() {
        String[] args = new String[0];
        AppApplication.main(args);
        assertEquals(args, AppApplication.args2);
    }

}
