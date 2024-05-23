package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.dto.*;
import br.com.sistema.clinica.ApiClinicSystem.paciente.Paciente;
import br.com.sistema.clinica.ApiClinicSystem.repository.IPacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    //@PageableDefault(page = 0, size = 10, sort = {"nome"})
    public Page<ListPacienteDTO> listPacienteDTOS(Pageable pageable) {
        return IPacienteRepository.findAllByAtivoTrue(pageable).map(ListPacienteDTO::new);
    }

    @PutMapping
    @Transactional
    public void updatePaciente(@RequestBody @Valid UpdatePacienteDTO updatePacienteDTO) {
        var paciente = IPacienteRepository.getReferenceById(updatePacienteDTO.id());
        paciente.updateInformationPaciente(updatePacienteDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePaciente(@PathVariable Long id) {
        var paciente = IPacienteRepository.getReferenceById(id);
        paciente.logicalExclusion();
    }
}