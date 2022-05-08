package com.example.demo;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class MainTest {

    @Test
    public void main() {
        String[] args = new String[0];
        AppApplication.main(args);
        assertEquals(args, AppApplication.args2);
    }
}
