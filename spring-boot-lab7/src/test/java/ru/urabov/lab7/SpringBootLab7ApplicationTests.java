package ru.urabov.lab7;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

// Для теста используется отдельная база в памяти, чтобы не трогать testdb/university
@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:testdb")
class SpringBootLab7ApplicationTests {

    @Test
    void contextLoads() {
    }
}
