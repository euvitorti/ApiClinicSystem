package br.com.sistema.clinica.ApiClinicSystem.doctor;

import br.com.sistema.clinica.ApiClinicSystem.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegistrationData(


        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        SpecialtyEnum specialty,

        @NotNull
        @Valid
        DataAddress address) {}
