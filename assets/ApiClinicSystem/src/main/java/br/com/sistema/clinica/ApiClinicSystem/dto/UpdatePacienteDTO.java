package br.com.sistema.clinica.ApiClinicSystem.dto;

import br.com.sistema.clinica.ApiClinicSystem.address.DataAddress;
import jakarta.validation.Valid;

public record UpdatePacienteDTO(
        Long id,
        String name,
        String phone,
        @Valid DataAddress dataAddress) {
}
