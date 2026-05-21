package com.tuempresa.medicamentos.mapper;

import com.tuempresa.medicamentos.dto.request.MedicamentoRequestDTO;
import com.tuempresa.medicamentos.dto.response.MedicamentoResponseDTO;
import com.tuempresa.medicamentos.entity.MedicamentoEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicamentoMapper {

    /**
     * Convierte DTO → Entity
     */
    public MedicamentoEntity toEntity(MedicamentoRequestDTO dto) {

        if (dto == null) return null;

        MedicamentoEntity entity = new MedicamentoEntity();

        entity.setNombreGenerico(dto.getNombreGenerico());
        entity.setMiligramos(dto.getMiligramos());
        entity.setDosis(dto.getDosis());
        entity.setFrecuencia(dto.getFrecuencia());
        entity.setHorario(dto.getHorario());
        entity.setFechaVencimiento(dto.getFechaVencimiento());

        return entity;
    }

    /**
     * Convierte Entity → ResponseDTO
     */
    public MedicamentoResponseDTO toDTO(MedicamentoEntity entity) {

        if (entity == null) return null;

        MedicamentoResponseDTO dto = new MedicamentoResponseDTO();

        dto.setId(entity.getId());
        dto.setNombreGenerico(entity.getNombreGenerico());
        dto.setMiligramos(entity.getMiligramos());
        dto.setDosis(entity.getDosis());
        dto.setFrecuencia(entity.getFrecuencia());
        dto.setHorario(entity.getHorario());
        dto.setFechaVencimiento(entity.getFechaVencimiento());

        return dto;
    }

    /**
     * Actualiza Entity existente con datos del DTO
     * (MUY IMPORTANTE para UPDATE limpio)
     */
    public void updateEntityFromDTO(
            MedicamentoRequestDTO dto,
            MedicamentoEntity entity) {

        entity.setNombreGenerico(dto.getNombreGenerico());
        entity.setMiligramos(dto.getMiligramos());
        entity.setDosis(dto.getDosis());
        entity.setFrecuencia(dto.getFrecuencia());
        entity.setHorario(dto.getHorario());
        entity.setFechaVencimiento(dto.getFechaVencimiento());
    }
}