package br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto;

import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;

import java.time.LocalDateTime;

public record ConsultationDetailsDTO(Long id, Long idDoctor, Long idPaciente, LocalDateTime date) {

    public ConsultationDetailsDTO(Consultation consultation) {
        this(consultation.getId(), consultation.getMedico().getId(), consultation.getPaciente().getId(), consultation.getData());
    }
}
