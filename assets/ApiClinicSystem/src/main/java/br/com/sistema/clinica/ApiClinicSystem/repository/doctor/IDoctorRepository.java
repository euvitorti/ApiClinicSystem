package br.com.sistema.clinica.ApiClinicSystem.repository.doctor;

import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.SpecialtyEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByAtivoTrue(Pageable pageable);

    @Query("""
    SELECT m FROM Medico m
    WHERE m.ativo = true AND m.especialidade = :especialidade AND m.id
    NOT IN(SELECT c.medico.id FROM Consulta c WHERE c.data = :data)
    ORDER BY RAND() LIMIT 1
    """)
    Doctor ChooseFreeDoctorOnTheDate(SpecialtyEnum especialidade, LocalDateTime data);

    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :idMedico")
    Boolean findAtivoById(Long idMedico);
}
