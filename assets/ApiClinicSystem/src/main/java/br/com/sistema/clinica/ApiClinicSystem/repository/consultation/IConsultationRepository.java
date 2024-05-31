package br.com.sistema.clinica.ApiClinicSystem.repository.consultation;

import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
}
