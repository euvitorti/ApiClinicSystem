package br.com.sistema.clinica.ApiClinicSystem.controller.paciente;

import br.com.sistema.clinica.ApiClinicSystem.dto.pacienteDto.ListPacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.pacienteDto.PacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.pacienteDto.PacienteDetailsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.pacienteDto.UpdatePacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.models.paciente.Paciente;
import br.com.sistema.clinica.ApiClinicSystem.repository.paciente.IPacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private IPacienteRepository IPacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PacienteDTO pacienteDTO, UriComponentsBuilder uriComponentsBuilder){
        var paciente = new Paciente(pacienteDTO);
        IPacienteRepository.save(paciente);

        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDetailsDTO(paciente));
    }

    @GetMapping
    //@PageableDefault(page = 0, size = 10, sort = {"nome"})
    public ResponseEntity<Page<ListPacienteDTO>> listPacienteDTOS(Pageable pageable) {
        var page = IPacienteRepository.findAllByAtivoTrue(pageable).map(ListPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePaciente(@RequestBody @Valid UpdatePacienteDTO updatePacienteDTO) {
        var paciente = IPacienteRepository.getReferenceById(updatePacienteDTO.id());
        paciente.updateInformationPaciente(updatePacienteDTO);

        return ResponseEntity.ok(new PacienteDetailsDTO(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable Long id) {
        var paciente = IPacienteRepository.getReferenceById(id);
        paciente.logicalExclusion();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity pacienteDetails(@PathVariable Long id) {
        var paciente = IPacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDetailsDTO(paciente));
    }
}