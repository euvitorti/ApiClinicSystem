package br.com.sistema.clinica.ApiClinicSystem.services.consultations;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;
import br.com.sistema.clinica.ApiClinicSystem.repository.doctor.IDoctorRepository;
import br.com.sistema.clinica.ApiClinicSystem.repository.paciente.IPacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// A CLASSE SERVICE EXECUTA AS REGRAS DE NEGÓCIO E VALIDAÇÕES DA APLICAÇÃO

@Service
public class ConsultationsService {

    @Autowired
    private IConsultationRepository iConsultationRepository;

    @Autowired
    private IDoctorRepository iDoctorRepository;

    @Autowired
    private IPacienteRepository iPacienteRepository;

    public void schedule(ScheduleAppointmentDTO scheduleAppointmentDTO){

        if (!iPacienteRepository.existsById(scheduleAppointmentDTO.idPaciente())) {
            throw new ValidationException("Id não encontrado.");
//            throw new ExceptionValidation("Id não encontrado.");
        }

        // O ID DO MÉDICO É OPCIONAL
        if (scheduleAppointmentDTO.idDoctor() != null && !iDoctorRepository.existsById(scheduleAppointmentDTO.idDoctor())) {
            throw new ValidationException("Id não encontrado.");
//            throw new ExceptionValidation("Id não encontrado.");
        }

        var paciente = iPacienteRepository.getReferenceById(scheduleAppointmentDTO.idPaciente());
        var doctor = ChooseDoctor(scheduleAppointmentDTO);

        var consultation = new Consultation(null, doctor, paciente, scheduleAppointmentDTO.date());

        iConsultationRepository.save(consultation);
    }

    // CASO NÃO TENHA UM MÉDICO REGISTRADO NA REQUISIÇÃO
    private Doctor ChooseDoctor(ScheduleAppointmentDTO scheduleAppointmentDTO) {

        if (scheduleAppointmentDTO.idDoctor() != null) {
            return iDoctorRepository.getReferenceById(scheduleAppointmentDTO.idDoctor());
        }

        if (scheduleAppointmentDTO.specialtyEnum() == null) {
            throw new ExceptionValidation("Especialidade não pode ser nula, caso o médico não seja escolhido.");
        }

        return iDoctorRepository.ChooseFreeDoctorOnTheDate(scheduleAppointmentDTO.specialtyEnum(), scheduleAppointmentDTO.date());
    }
}
