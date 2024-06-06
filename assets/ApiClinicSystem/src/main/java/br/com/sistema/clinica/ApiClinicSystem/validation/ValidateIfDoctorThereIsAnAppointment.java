package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// VALIDAR SE EXISTE UMA MARCAÇÃO NO MESMO HORÁRIO

@Component
public class ValidateIfDoctorThereIsAnAppointment implements AppointmentValidator{

    @Autowired
    private IConsultationRepository iConsultationRepository;

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO){

        var doctorHasAnotherAppointment =  iConsultationRepository.existsByDoctor(scheduleAppointmentDTO.idDoctor(), scheduleAppointmentDTO.date());

        if (doctorHasAnotherAppointment) {
            throw new ExceptionValidation("Médico já possui outra consulta neste mesmo horário.");
        }
    }
}
