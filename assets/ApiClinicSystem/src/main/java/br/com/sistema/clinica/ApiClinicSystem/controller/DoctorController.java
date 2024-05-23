package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.dto.DoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.ListDoctorsDTO;
import br.com.sistema.clinica.ApiClinicSystem.repository.IDoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return iDoctorRepository.findAll(pageable).map(ListDoctorsDTO::new);
    }
}
