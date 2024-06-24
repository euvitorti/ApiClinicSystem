package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentValidator {

    void validate(ScheduleAppointmentDTO scheduleAppointmentDTO);
}
