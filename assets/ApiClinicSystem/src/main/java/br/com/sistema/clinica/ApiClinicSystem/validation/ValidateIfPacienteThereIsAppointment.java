package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;

public class ValidateIfPacienteThereIsAppointment {

    private IConsultationRepository iConsultationRepository;

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {
        var firstHour = scheduleAppointmentDTO.date().withHour(7);
        var lasthour  = scheduleAppointmentDTO.date().withHour(18);
        var pacienteHasAnotherAppointment = iConsultationRepository.existsByPaciente(scheduleAppointmentDTO.idPaciente(), firstHour, lasthour);

        if (pacienteHasAnotherAppointment) {
            throw new ExceptionValidation("Paciente já possui uma consulta neste mesmo horário.");
        }
    }
}
