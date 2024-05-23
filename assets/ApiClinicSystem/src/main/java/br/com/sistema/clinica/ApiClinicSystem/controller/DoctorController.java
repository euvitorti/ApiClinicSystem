package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.doctor.Doctor;
import br.com.sistema.clinica.ApiClinicSystem.dto.DoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.ListDoctorsDTO;
import br.com.sistema.clinica.ApiClinicSystem.repository.IDoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ListDoctorsDTO> doctorList() {

        // CONVERSÃO, POIS ESTÁ RECEBENDO UMA LISTA DE MÉDICOS

        return iDoctorRepository.findAll().stream().map(ListDoctorsDTO::new).toList();
    }
}
