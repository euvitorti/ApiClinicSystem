package br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto;

import br.com.sistema.clinica.ApiClinicSystem.models.doctor.SpecialtyEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleAppointmentDTO(
        Long idDoctor,

        @NotNull
        Long idPaciente,

        @NotNull
        // DATA FUTURA
        @Future
        // @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date,

        SpecialtyEnum specialtyEnum)
{ }
