package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

// O PACIENTE SÓ PODE MARCAR A CONSULTA COM 30 MINUTOS DE ANTECEDÊNCIA

@Component
public class ValidateAppointmentTimeAdvance implements AppointmentValidator{
    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {

        var data = scheduleAppointmentDTO.data();
        var hourNow = LocalDateTime.now();

        var differenceMinutes = Duration.between(hourNow, data).toMinutes();

        if (differenceMinutes < 30) {
            throw new ExceptionValidation("Consulta deve ser agendada no mínimo com 30 minutos de antecedência.");
        }
    }
}
