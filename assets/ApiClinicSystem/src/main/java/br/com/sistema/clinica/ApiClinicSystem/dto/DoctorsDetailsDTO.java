package br.com.sistema.clinica.ApiClinicSystem.dto;

import br.com.sistema.clinica.ApiClinicSystem.address.Address;
import br.com.sistema.clinica.ApiClinicSystem.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.doctor.SpecialtyEnum;

public record DoctorsDetailsDTO(
        Long Id,
        String name,
        String email,
        String phone,
        String crm,
        SpecialtyEnum specialty,
        Address address) {

    public DoctorsDetailsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getTelefone(), doctor.getCrm(), doctor.getEspecialidade(), doctor.getEndereco());
    }
}
