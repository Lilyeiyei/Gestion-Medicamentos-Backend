package co.vinni.pacientes.infraestructura.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PacienteDto(
        @NotBlank(message = "El nombre completo es requerido")
        String nombreCompleto,

        @NotBlank(message = "El documento de identidad es requerido")
        @Pattern(regexp = "\\d+", message = "El documento de identidad solo debe contener números")
        String documentoIdentidad,

        @NotBlank(message = "La EPS es requerida")
        String eps,

        @Email(message = "El formato del correo es incorrecto")
        @NotBlank(message = "El correo electrónico es requerido")
        String email,

        @NotBlank(message = "La contraseña es requerida")
        String contrasena
) {}
