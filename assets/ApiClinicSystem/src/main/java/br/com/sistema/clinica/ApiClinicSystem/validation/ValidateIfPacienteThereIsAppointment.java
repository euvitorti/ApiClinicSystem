package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// VERIFICAR SE O PACIENTE JÁ POSSUI UMA CONSULTA NO DIA

@Component
public class ValidateIfPacienteThereIsAppointment implements AppointmentValidator{

    @Autowired
    private IConsultationRepository iConsultationRepository;

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {
        var firstHour = scheduleAppointmentDTO.data().withHour(7);
        var lasthour  = scheduleAppointmentDTO.data().withHour(18);
        var pacienteHasAnotherAppointment = iConsultationRepository.existsByPacienteAndDataBetween(scheduleAppointmentDTO.idPaciente(), firstHour, lasthour);

        if (pacienteHasAnotherAppointment) {
            throw new ExceptionValidation("Paciente já possui uma consulta neste mesmo horário.");
        }
    }
}
