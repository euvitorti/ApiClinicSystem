package br.com.sistema.clinica.ApiClinicSystem.repository.consultation;

import br.com.sistema.clinica.ApiClinicSystem.models.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IConsultationRepository extends JpaRepository<Consultation, Long> {

    // TODO
    // MÉTODOS COM ERRO
//    Boolean existsByPacienteAndDataBetween(Long idPaciente, LocalDateTime firstHour, LocalDateTime lastHour);

//    Boolean existsByIdMedico(Long idMedico, LocalDateTime data);
}
