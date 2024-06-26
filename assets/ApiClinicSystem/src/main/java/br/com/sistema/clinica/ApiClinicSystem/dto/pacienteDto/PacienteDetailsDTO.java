package br.com.sistema.clinica.ApiClinicSystem.dto.pacienteDto;

import br.com.sistema.clinica.ApiClinicSystem.models.address.Address;
import br.com.sistema.clinica.ApiClinicSystem.models.address.DataAddress;
import br.com.sistema.clinica.ApiClinicSystem.models.paciente.Paciente;

public record PacienteDetailsDTO(
        String name,
        String email,
        String phone,
        String cpf,
        Address address) {

    public PacienteDetailsDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
