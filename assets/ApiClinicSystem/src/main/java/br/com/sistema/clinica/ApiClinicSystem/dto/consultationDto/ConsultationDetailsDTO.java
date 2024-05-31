package br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto;

import java.time.LocalDate;

public record ConsultationDetailsDTO(Long id, Long idDoctor, Long idPaciente, LocalDate date) {
}
