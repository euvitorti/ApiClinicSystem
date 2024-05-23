package br.com.sistema.clinica.ApiClinicSystem.repository;

import br.com.sistema.clinica.ApiClinicSystem.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
