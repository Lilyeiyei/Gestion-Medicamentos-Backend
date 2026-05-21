package com.tuempresa.medicamentos.service.impl;

import com.tuempresa.medicamentos.dto.request.MedicamentoRequestDTO;
import com.tuempresa.medicamentos.dto.response.MedicamentoResponseDTO;
import com.tuempresa.medicamentos.entity.MedicamentoEntity;
import com.tuempresa.medicamentos.mapper.MedicamentoMapper;
import com.tuempresa.medicamentos.repository.MedicamentoRepository;
import com.tuempresa.medicamentos.service.MedicamentoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class MedicamentoServiceImpl
        implements MedicamentoService {

    @Inject
    MedicamentoRepository repository;

    @Inject
    MedicamentoMapper mapper;

    /**
     * Registrar medicamento
     */
    @Override
    public MedicamentoResponseDTO registrar(
            MedicamentoRequestDTO dto) {

        // VALIDAR DUPLICADOS
        if (repository.buscarPorNombre(
                        dto.getNombreGenerico())
                .isPresent()) {

            throw new RuntimeException(
                    "Medicamento ya se encuentra registrado"
            );
        }

        // VALIDAR FECHA VENCIMIENTO
        if (dto.getFechaVencimiento()
                .isBefore(LocalDate.now())) {

            throw new RuntimeException(
                    "El medicamento está vencido"
            );
        }

        // CONVERTIR DTO → ENTITY
        MedicamentoEntity entity =
                mapper.toEntity(dto);

        // FECHA CREACIÓN
        entity.setFechaCreacion(
                LocalDateTime.now());

        // GUARDAR EN BD
        repository.persist(entity);

        // RETORNAR DTO
        return mapper.toDTO(entity);
    }

    /**
     * Listar medicamentos
     */
    @Override
    public List<MedicamentoResponseDTO> listar() {

        return repository.listAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Actualizar medicamento
     */
    @Override
    public MedicamentoResponseDTO actualizar(
            Long id,
            MedicamentoRequestDTO dto) {

        // BUSCAR MEDICAMENTO
        MedicamentoEntity entity =
                repository.findById(id);

        // VALIDAR EXISTENCIA
        if (entity == null) {

            throw new RuntimeException(
                    "Medicamento no encontrado"
            );
        }

        // VALIDAR FECHA
        if (dto.getFechaVencimiento()
                .isBefore(LocalDate.now())) {

            throw new RuntimeException(
                    "El medicamento está vencido"
            );
        }

        // ACTUALIZAR CAMPOS
        mapper.updateEntityFromDTO(dto, entity);

        // GUARDAR CAMBIOS
        repository.persist(entity);

        // RETORNAR DTO
        return mapper.toDTO(entity);
    }

    /**
     * Eliminar medicamento
     */
    @Override
    public void eliminar(Long id) {

        boolean eliminado =
                repository.deleteById(id);

        if (!eliminado) {

            throw new RuntimeException(
                    "Medicamento no encontrado"
            );
        }
    }
}