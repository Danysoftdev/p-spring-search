package com.eam.p_spring_search.controller;

import com.eam.p_spring_search.entity.Producto;
import com.eam.p_spring_search.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<?> obtenerProducto(@PathVariable String codigo) {
        try {
            Producto producto = productoService.obtenerPorCodigo(codigo);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Producto obtenido exitosamente.");
            response.put("producto", producto);

            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
