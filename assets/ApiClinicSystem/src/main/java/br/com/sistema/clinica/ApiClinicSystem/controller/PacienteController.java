package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.dto.PacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.paciente.Paciente;
import br.com.sistema.clinica.ApiClinicSystem.repository.IPacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private IPacienteRepository IPacienteRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PacienteDTO pacienteDTO){
        IPacienteRepository.save(new Paciente(pacienteDTO));
    }
}