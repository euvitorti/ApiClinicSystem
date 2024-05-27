package br.com.sistema.clinica.ApiClinicSystem.dto;

import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.SpecialtyEnum;

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
