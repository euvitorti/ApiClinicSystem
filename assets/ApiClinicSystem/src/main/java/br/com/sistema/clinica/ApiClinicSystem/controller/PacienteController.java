package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.dto.ListPacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.PacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.paciente.Paciente;
import br.com.sistema.clinica.ApiClinicSystem.repository.IPacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<ListPacienteDTO> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao) {
        return IPacienteRepository.findAll(paginacao).map(ListPacienteDTO::new);
    }
}