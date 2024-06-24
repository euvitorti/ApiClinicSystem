package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.paciente.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// VALIDAR SE O PACIENTE ESTÁ ATIVO

@Component
public class ValidateActivePaciente implements AppointmentValidator{

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @Override
    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {

        var activePaciente = iPacienteRepository.findAtivoById(scheduleAppointmentDTO.idPaciente());
        if (!activePaciente) {
            throw new ExceptionValidation("Consulta não pode ser agendado com paciente excluído.");
        }
    }
}

