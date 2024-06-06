package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;

import java.time.DayOfWeek;

// VALIDAR O HORÁRIO DO FUNCIONAMENTO DA CLÍNICA
public class ValidateAppointmentTime {

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {

        var data = scheduleAppointmentDTO.date();
        var sunday = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheOpening = data.getHour() < 7;
        var afterTheOpening = data.getHour() > 18;

        if (sunday || beforeTheOpening || afterTheOpening) {
            throw new ExceptionValidation("Consulta fora do horário de funcionamento.");
        }
    }
}
