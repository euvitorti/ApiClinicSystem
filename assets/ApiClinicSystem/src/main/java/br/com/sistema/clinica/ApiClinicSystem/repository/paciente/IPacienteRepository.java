package br.com.sistema.clinica.ApiClinicSystem.repository.paciente;

import br.com.sistema.clinica.ApiClinicSystem.models.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            SELECT p.ativo
            FROM Paciente p
            WHERE
            p.id = :idPaciente
            """)
    Boolean findActiveById(Long idPaciente);
}

