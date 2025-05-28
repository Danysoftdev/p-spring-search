package com.eam.p_spring_search.service;

import com.eam.p_spring_search.entity.Producto;
import com.eam.p_spring_search.repository.ProductoRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductoServiceIT {

    @Container
    static MariaDBContainer<?> mariadb = new MariaDBContainer<>("mariadb:10.6")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mariadb::getJdbcUrl);
        registry.add("spring.datasource.username", mariadb::getUsername);
        registry.add("spring.datasource.password", mariadb::getPassword);
    }

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void cleanDB() {
        productoRepository.deleteAll();
    }

    // 3. Obtener por código
    @Test
    @Order(1)
    void obtenerPorCodigo_DeberiaRetornarProducto() {
        productoRepository.save(new Producto(null, "B001", "Blusa", 60000.0, 8));
        Producto encontrado = productoService.obtenerPorCodigo("B001");

        assertEquals("Blusa", encontrado.getNombre());
    }

    @Test
    @Order(2)
    void obtenerPorCodigo_DeberiaFallarSiNoExiste() {
        Exception ex = assertThrows(NoSuchElementException.class, () -> {
            productoService.obtenerPorCodigo("NOEXISTE");
        });

        assertEquals("No se encontró el producto con el código proporcionado", ex.getMessage());
    }

}
