package br.com.sistema.clinica.ApiClinicSystem.services.consultations;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.DataCancelConsultationDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.error.ExceptionValidation;
import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;
import br.com.sistema.clinica.ApiClinicSystem.repository.doctor.IDoctorRepository;
import br.com.sistema.clinica.ApiClinicSystem.repository.paciente.IPacienteRepository;
import br.com.sistema.clinica.ApiClinicSystem.validation.AppointmentValidator;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// A CLASSE SERVICE EXECUTA AS REGRAS DE NEGÓCIO E VALIDAÇÕES DA APLICAÇÃO

@Service
public class ConsultationsService {

    @Autowired
    private IConsultationRepository iConsultationRepository;

    @Autowired
    private IDoctorRepository iDoctorRepository;

    @Autowired
    private IPacienteRepository iPacienteRepository;

    // O SPRING IRÁ INICIALIZAR CADA VALIDADOR
    @Autowired
    private List<AppointmentValidator> appointmentValidatorList;

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

        // TODO PRINCÍPIO SOLID, 3 ESTÃO SENDO APLICADOS - SOD, DEFINIÇÃO LOGO A SEGUIR

        //--------------------------------------------------------------------------------------------------------------
        // AO FAZER ISSO, APLICAMOS 3 PRINCÍPIOS DO SOLID
        // SINGLE RESPONSABILITY PRINCIPLE (PRINCÍPIO DA RESPONSABILIDADE ÚNICA): POIS CADA CLASSE DO VALIDADOR
        // EXECUTA APENAS UMA REPONSABILIDADE)
        //--------------------------------------------------------------------------------------------------------------
        // OPEN-CLOSED PRINCIPLE (PRINCÍPIO ABERTO-FECHADO): A CLASSE ConsultationSService, ESTÁ FECHADA PARA
        // MODIFICAÇÃO, PORÉM ABERTA PARA EXTENSÃO. PODE REBER NOVOS VALIDADORES SEM PRECISAR MODIFICAR ELA PRÓPRIA
        //--------------------------------------------------------------------------------------------------------------
        // DEPENDENCY INVERSION PRINCIPLE (PRINCÍPIO DA INVERSÃO DE DEPENDÊNCIA): A CLASSE ConsultationService,
        // ELA DEPENDE DE UMA ABSTRAÇÃO, NO CASO A CLASSE AppointmentValidator, NÃO PRECISA NECESSARIAMENTE
        // DOS VALIDADORES EM ESPECÍFICO
        //--------------------------------------------------------------------------------------------------------------

        // PERCORRENDO O VALIDADOR
        appointmentValidatorList.forEach(v -> v.validate(scheduleAppointmentDTO));

        var paciente = iPacienteRepository.getReferenceById(scheduleAppointmentDTO.idPaciente());
        var doctor = ChooseDoctor(scheduleAppointmentDTO);

        var consultation = new Consultation(null, doctor, paciente, scheduleAppointmentDTO.date(), null);
//        var consultation = new Consultation(null, doctor, paciente, scheduleAppointmentDTO.date());

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

    public void cancel(DataCancelConsultationDTO data) {
        if (!iConsultationRepository.existsById(data.idConsulta())) {
            throw new ExceptionValidation("Id da consulta informado não existe!");
        }

        var consultation = iConsultationRepository.getReferenceById(data.idConsulta());
        consultation.cancel(data.cancellationReason());
    }
}
