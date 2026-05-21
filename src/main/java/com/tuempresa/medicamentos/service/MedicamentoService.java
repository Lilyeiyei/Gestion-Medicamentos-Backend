package com.tuempresa.medicamentos.service;

import com.tuempresa.medicamentos.dto.request.MedicamentoRequestDTO;
import com.tuempresa.medicamentos.dto.response.MedicamentoResponseDTO;

import java.util.List;

public interface MedicamentoService {

    MedicamentoResponseDTO registrar(
            MedicamentoRequestDTO dto
    );

    List<MedicamentoResponseDTO> listar();

    MedicamentoResponseDTO actualizar(
            Long id,
            MedicamentoRequestDTO dto
    );

    void eliminar(Long id);
}