package br.com.sistema.clinica.ApiClinicSystem.repository;

import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByAtivoTrue(Pageable pageable);
}
