package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;

public interface AppointmentValidator {

    void validate(ScheduleAppointmentDTO scheduleAppointmentDTO);
}
