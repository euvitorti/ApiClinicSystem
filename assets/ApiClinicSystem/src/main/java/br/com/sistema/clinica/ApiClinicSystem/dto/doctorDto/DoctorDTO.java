package br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto;

import br.com.sistema.clinica.ApiClinicSystem.models.address.DataAddress;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.SpecialtyEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotBlank(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull(message = "Especialidade é obrigatório")
        SpecialtyEnum specialty,

        @NotNull(message = "Endereço é obrigatório")
        @Valid
        DataAddress address) {}
