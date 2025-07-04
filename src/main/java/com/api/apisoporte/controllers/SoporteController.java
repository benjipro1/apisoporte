package com.api.apisoporte.controllers;

import com.api.apisoporte.dto.SoporteDTO;
import com.api.apisoporte.services.SoporteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

    @GetMapping("/hateoas/{id}")
    public SoporteDTO obtenerHateoas(@PathVariable Integer id) {
        SoporteDTO dto = soporteServices.buscar(id);
        dto.add(linkTo(methodOn(SoporteController.class).obtenerHateoas(id)).withSelfRel());
        dto.add(linkTo(methodOn(SoporteController.class).listarHateoas()).withRel("TODOS"));
        dto.add(linkTo(methodOn(SoporteController.class).eliminar(id)).withRel("ELIMINAR"));
        return dto;
    }

    @GetMapping("/hateoas")
    public List<SoporteDTO> listarHateoas() {
        List<SoporteDTO> soportes = soporteServices.listar();
        for (SoporteDTO dto : soportes) {
            dto.add(linkTo(methodOn(SoporteController.class).obtenerHateoas(dto.getIdSoporte())).withSelfRel());
            dto.add(linkTo(methodOn(SoporteController.class).listarHateoas()).withRel("TODOS"));
            dto.add(linkTo(methodOn(SoporteController.class).eliminar(dto.getIdSoporte())).withRel("ELIMINAR"));
        }
        return soportes;
    }


}
