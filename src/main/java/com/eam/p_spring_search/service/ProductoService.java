package com.eam.p_spring_search.service;

import com.eam.p_spring_search.entity.Producto;
import com.eam.p_spring_search.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener un producto por su código (con validación si no existe)
    public Producto obtenerPorCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el producto con el código proporcionado"));
    }

}
