package br.com.sistema.clinica.ApiClinicSystem.validation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.repository.paciente.IPacienteRepository;

// VALIDAR SE O PACIENTE ESTÁ ATIVO
public class ValidateActivePaciente {

    private IPacienteRepository iPacienteRepository;

    public void validate(ScheduleAppointmentDTO scheduleAppointmentDTO) {

        var activePaciente = iPacienteRepository.findActiveById(scheduleAppointmentDTO.idPaciente());
        if (!activePaciente) {
            throw new ExceptionValidation("Consulta não pode ser agendado com paciente excluído.");
        }
    }
}

