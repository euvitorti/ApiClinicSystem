package br.com.sistema.clinica.ApiClinicSystem.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<ApiClinicSystemDoctor, Long> {
}
