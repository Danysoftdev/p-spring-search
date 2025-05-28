package com.eam.p_spring_search.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.NoSuchElementException;

import com.eam.p_spring_search.repository.ProductoRepository;
import com.eam.p_spring_search.service.ProductoService;
import com.eam.p_spring_search.entity.Producto;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void obtenerProductoPorCodigo_DeberiaRetornarProductoSiExiste() {
        Producto producto = new Producto(1L, "P001", "Camisa", 50000.0, 10);
        when(productoRepository.findByCodigo("P001")).thenReturn(Optional.of(producto));

        Producto resultado = productoService.obtenerPorCodigo("P001");

        assertNotNull(resultado);
        assertEquals("Camisa", resultado.getNombre());
        assertEquals("P001", resultado.getCodigo());
        verify(productoRepository).findByCodigo("P001");
    }

    @Test
    void obtenerProductoPorCodigo_DeberiaLanzarExcepcionSiNoExiste() {
        when(productoRepository.findByCodigo("P999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            productoService.obtenerPorCodigo("P999");
        });

        assertEquals("No se encontró el producto con el código proporcionado", exception.getMessage());
        verify(productoRepository).findByCodigo("P999");
    }

}
