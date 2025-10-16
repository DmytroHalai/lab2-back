package org.example.lab2back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Lab2BackApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void correctTest() {
        assertEquals(2, 3);
    }

}
