package br.com.sistema.clinica.ApiClinicSystem.services.consultations;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;
import br.com.sistema.clinica.ApiClinicSystem.repository.consultation.IConsultationRepository;
import br.com.sistema.clinica.ApiClinicSystem.repository.doctor.IDoctorRepository;
import br.com.sistema.clinica.ApiClinicSystem.repository.paciente.IPacienteRepository;
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

        var paciente = iPacienteRepository.findById(scheduleAppointmentDTO.idPaciente()).get();
        var doctor = iDoctorRepository.findById(scheduleAppointmentDTO.idDoctor()).get();

        var consultation = new Consultation(null, doctor, paciente, scheduleAppointmentDTO.date());

        iConsultationRepository.save(consultation);
    }
}
