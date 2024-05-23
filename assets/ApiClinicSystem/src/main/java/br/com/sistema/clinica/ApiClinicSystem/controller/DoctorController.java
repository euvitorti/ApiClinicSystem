package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.dto.DoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.ListDoctorsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.UpdateDoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.repository.IDoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    @Autowired
    private IDoctorRepository iDoctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorDTO doctorDTO) {
        iDoctorRepository.save(new Doctor(doctorDTO));
    }

    @GetMapping
    public Page<ListDoctorsDTO> doctorList(Pageable pageable) {

        // CONVERSÃO, POIS ESTÁ RECEBENDO UMA LISTA DE MÉDICOS
        // PAGINAÇÃO, USA A CLASSE OAGEABLE DO SPRING, CUIDADO AO IMPORTAR, POIS EXISTE UMA CLASSE COM O MESMO NOME DO JAVA
        // ORDENAÇÃO É PASSADA PELA URL ?sort=nome
        // TODO @PageableDefault

        //BUSCAR SOMENTE OS ATIVOS
        return iDoctorRepository.findAllByAtivoTrue(pageable).map(ListDoctorsDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDTO updateDoctorDTO) {

        // BUSCANDO O MÉDICO PELO ID
        var doctor = iDoctorRepository.getReferenceById(updateDoctorDTO.id());

        // ATUALIZA OS DADOS
        doctor.updateDoctor(updateDoctorDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        var doctor = iDoctorRepository.getReferenceById(id);

        doctor.logicalExclusion();
    }
}
