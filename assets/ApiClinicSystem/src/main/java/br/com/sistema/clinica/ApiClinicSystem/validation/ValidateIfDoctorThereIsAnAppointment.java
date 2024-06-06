package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;

// VALIDAR SE EXISTE UMA MARCAÇÃO NO MESMO HORÁRIO
public class ValidateIfDoctorThereIsAnAppointment {

    private IConsultationRepository iConsultationRepository;

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO){

        var doctorHasAnotherAppointment =  iConsultationRepository.existsByDoctor(scheduleAppointmentDTO.idDoctor(), scheduleAppointmentDTO.date());

        if (doctorHasAnotherAppointment) {
            throw new ExceptionValidation("Médico já possui outra consulta neste mesmo horário.");
        }
    }
}
