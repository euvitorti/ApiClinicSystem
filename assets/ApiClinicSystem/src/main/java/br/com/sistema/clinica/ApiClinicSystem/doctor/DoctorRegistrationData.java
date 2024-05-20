package br.com.sistema.clinica.ApiClinicSystem.doctor;

import br.com.sistema.clinica.ApiClinicSystem.address.Address;

public record DoctorRegistrationData(
        String name,
        String email,
        String crm,
        SpecialtyEnum specialty,
        Address address) {}
