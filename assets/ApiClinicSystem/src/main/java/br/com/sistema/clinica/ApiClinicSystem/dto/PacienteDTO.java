package br.com.sistema.clinica.ApiClinicSystem.dto;

import br.com.sistema.clinica.ApiClinicSystem.address.DataAddress;
import br.com.sistema.clinica.ApiClinicSystem.doctor.SpecialtyEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotNull
        @Valid
        DataAddress address
) {
}
