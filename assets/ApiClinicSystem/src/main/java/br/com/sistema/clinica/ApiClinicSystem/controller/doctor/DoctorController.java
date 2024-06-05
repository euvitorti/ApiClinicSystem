package br.com.sistema.clinica.ApiClinicSystem.controller.doctor;

import br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto.DoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto.DoctorsDetailsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto.ListDoctorsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto.UpdateDoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.repository.doctor.IDoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// CLASSE CONTROLLER NÃO DEVE TER REGRAS DE NOGÓCIO DA APLICAÇÃO

@RestController
@RequestMapping("medicos")
public class DoctorController {

    @Autowired
    private IDoctorRepository iDoctorRepository;

    // PADRONIZANDO O RETORNO DA API
//    ResponseEntity

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorDTO doctorDTO, UriComponentsBuilder uriComponentsBuilder) {

        // CÓDIGO 201 DEVOLVE NO CORPO DA RESPOSTA OS DADOS DO NOVO RECURSO/REGISTRO CRIADO
        // DEVOLVE TAMBÉM UM CABEÇALHO DO PROTOCOLO HTTP (LOCATION)

        var doctor = new Doctor(doctorDTO);

        iDoctorRepository.save(doctor);

        // É O ENDEREÇO DA API
        // UriComponentsBuilder - FICA ENCARREGADO PARA SABER QUAL É O ENDEREÇO AUTOMATICAMENTE
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorsDetailsDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorsDTO>> doctorList(Pageable pageable) {

        // CONVERSÃO, POIS ESTÁ RECEBENDO UMA LISTA DE MÉDICOS
        // PAGINAÇÃO, USA A CLASSE OAGEABLE DO SPRING, CUIDADO AO IMPORTAR, POIS EXISTE UMA CLASSE COM O MESMO NOME DO JAVA
        // ORDENAÇÃO É PASSADA PELA URL ?sort=nome
        // TODO @PageableDefault

        //BUSCAR SOMENTE OS ATIVOS
        var page = iDoctorRepository.findAllByAtivoTrue(pageable).map(ListDoctorsDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {

        // BUSCANDO O MÉDICO PELO ID
        var doctor = iDoctorRepository.getReferenceById(updateDoctorDTO.id());

        // ATUALIZA OS DADOS
        doctor.updateDoctor(updateDoctorDTO);

        return ResponseEntity.ok(new DoctorsDetailsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var doctor = iDoctorRepository.getReferenceById(id);

        doctor.logicalExclusion();

        // 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity doctorDetails(@PathVariable Long id) {
        var doctor = iDoctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorsDetailsDTO(doctor));
    }
}
