package br.com.sistema.clinica.ApiClinicSystem.repository.consultation;

import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {

    Boolean existsByPaciente(Long idPaciente, LocalDateTime firstHour, LocalDateTime lastHour);

    Boolean existsByDoctor(Long idDoctor, LocalDateTime localDateTime);
}
