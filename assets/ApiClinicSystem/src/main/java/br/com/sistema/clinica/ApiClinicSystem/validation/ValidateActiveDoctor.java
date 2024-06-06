package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.doctor.IDoctorRepository;

// VALIDAR SE O MÉDICO ESTÁ ATIVO
public class ValidateActiveDoctor {
    private IDoctorRepository iDoctorRepository;

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {
        // ESCOLHA DO MÉDICO OPCIONAL
        if (scheduleAppointmentDTO.idDoctor() == null) {
            return;
        }

        var activeDoctor = iDoctorRepository.findActiveById(scheduleAppointmentDTO.idDoctor());
        if (!activeDoctor) {
            throw new ExceptionValidation("Consulta não pode ser agendado com médico excluído.");
        }
    }
}
