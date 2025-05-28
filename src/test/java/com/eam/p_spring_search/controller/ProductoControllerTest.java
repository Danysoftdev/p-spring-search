package com.eam.p_spring_search.controller;

import com.eam.p_spring_search.entity.Producto;
import com.eam.p_spring_search.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void obtenerProducto_DeberiaRetornarProductoSiExiste() throws Exception {
        Producto producto = new Producto(1L, "P001", "Camisa", 50000.0, 10);

        when(productoService.obtenerPorCodigo("P001")).thenReturn(producto);

        mockMvc.perform(get("/api/productos/obtener/P001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto.codigo").value("P001"));
    }

    @Test
    void obtenerProducto_DeberiaRetornarNotFoundSiNoExiste() throws Exception {
        when(productoService.obtenerPorCodigo("P999"))
                .thenThrow(new NoSuchElementException("No existe"));

        mockMvc.perform(get("/api/productos/obtener/P999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No existe"));
    }

}
