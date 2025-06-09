package com.api.apisoporte.controllers;

import com.api.apisoporte.dto.SoporteDTO;
import com.api.apisoporte.services.SoporteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteServices soporteServices;

    @PostMapping
    public ResponseEntity<SoporteDTO> crear(@RequestBody SoporteDTO dto) {
        return ResponseEntity.ok(soporteServices.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<SoporteDTO>> listar() {
        return ResponseEntity.ok(soporteServices.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoporteDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(soporteServices.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoporteDTO> actualizar(@PathVariable Integer id, @RequestBody SoporteDTO dto) {
        return ResponseEntity.ok(soporteServices.actualizar(id, dto));
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        soporteServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
