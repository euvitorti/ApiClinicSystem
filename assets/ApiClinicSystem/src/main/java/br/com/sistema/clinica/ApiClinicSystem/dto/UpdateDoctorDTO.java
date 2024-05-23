package br.com.sistema.clinica.ApiClinicSystem.dto;

import br.com.sistema.clinica.ApiClinicSystem.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        DataAddress dataAddress) {
}
