package br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto;

import br.com.sistema.clinica.ApiClinicSystem.models.consultation.CancellationReason;
import jakarta.validation.constraints.NotNull;

public record DataCancelConsultationDTO(
        @NotNull
        Long idConsulta,

        @NotNull
        CancellationReason cancellationReason) {}
