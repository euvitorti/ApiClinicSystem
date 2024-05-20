package br.com.sistema.clinica.ApiClinicSystem.doctor;

import br.com.sistema.clinica.ApiClinicSystem.address.DataAddress;


public record DoctorRegistrationData(
        String name,
        String email,
        String crm,
        SpecialtyEnum specialty,
        DataAddress address) {}
