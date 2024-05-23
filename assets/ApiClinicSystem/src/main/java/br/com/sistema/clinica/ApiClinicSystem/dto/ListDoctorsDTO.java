package br.com.sistema.clinica.ApiClinicSystem.dto;

import br.com.sistema.clinica.ApiClinicSystem.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.doctor.SpecialtyEnum;

public record ListDoctorsDTO(
        Long id,
        String name,
        String email,
        String crm,
        SpecialtyEnum specialty) {

    public ListDoctorsDTO(Doctor doctor){
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }
}
