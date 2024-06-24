package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.doctor.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// VALIDAR SE O MÉDICO ESTÁ ATIVO

@Component
public class ValidateActiveDoctor implements AppointmentValidator{

    @Autowired
    private IDoctorRepository iDoctorRepository;

    @Override
    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {
        // ESCOLHA DO MÉDICO OPCIONAL
        if (scheduleAppointmentDTO.idDoctor() == null) {
            return;
        }

        var activeDoctor = iDoctorRepository.findAtivoById(scheduleAppointmentDTO.idDoctor());
        if (!activeDoctor) {
            throw new ExceptionValidation("Consulta não pode ser agendado com médico excluído.");
        }
    }
}
