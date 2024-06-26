
package br.com.sistema.clinica.ApiClinicSystem.dto.pacienteDto;

import br.com.sistema.clinica.ApiClinicSystem.models.address.DataAddress;
import jakarta.validation.Valid;

public record UpdatePacienteDTO(
        Long id,
        String name,
        String phone,
        @Valid DataAddress dataAddress) {
}
