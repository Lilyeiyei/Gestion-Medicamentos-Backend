package com.tuempresa.medicamentos.dto.response;

public class MensajeResponseDTO {

    private String mensaje;

    public MensajeResponseDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}