package br.com.sistema.clinica.ApiClinicSystem.repository;

import br.com.sistema.clinica.ApiClinicSystem.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}
