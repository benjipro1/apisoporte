package com.api.apisoporte.services;

import com.api.apisoporte.models.Soporte;
import com.api.apisoporte.models.Usuario;
import com.api.apisoporte.repository.SoporteRepository;
import com.api.apisoporte.repository.UsuarioRepository;
import com.api.apisoporte.dto.SoporteDTO;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class SoporteServices {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SoporteRepository soporterepository;

    private SoporteDTO toDTO(Soporte soporte) {
    return new SoporteDTO(
        soporte.getIdSoporte(),
        soporte.getUsuario().getIdUsuario(),
        soporte.getDescripcion(),
        soporte.getTipo(),
        soporte.getEstado(),
        soporte.getFechaCreacion(),
        soporte.getFechaResolucion()
    );
}

    private Soporte toEntity(SoporteDTO dto) {
        Soporte soporte = new Soporte();
        soporte.setIdSoporte(dto.getIdSoporte());
        soporte.setFechaCreacion(dto.getFechaCreacion());
        soporte.setFechaResolucion(dto.getFechaResolucion());
        soporte.setDescripcion(dto.getDescripcion());
        soporte.setTipo(dto.getTipo());
        soporte.setEstado(dto.getEstado());
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        soporte.setUsuario(usuario);
        return soporte;
    }

    public SoporteDTO crear(SoporteDTO dto) {
        Soporte soporte = toEntity(dto);
        return toDTO(soporterepository.save(soporte));
    }

    public List<SoporteDTO> listar() {
        return soporterepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public SoporteDTO buscar(Integer id) {
        Soporte soporte = soporterepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        return toDTO(soporte);
    }

    public SoporteDTO actualizar(Integer id, SoporteDTO dto) {
        Soporte existente = soporterepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
        existente.setFechaCreacion(dto.getFechaCreacion());
        existente.setFechaResolucion(dto.getFechaResolucion());
        existente.setDescripcion(dto.getDescripcion());
        existente.setTipo(dto.getTipo());
        existente.setEstado(dto.getEstado());
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    existente.setUsuario(usuario);
    return toDTO(soporterepository.save(existente));
    }

    public void eliminar(Integer id) {
        soporterepository.deleteById(id);
    }
}
