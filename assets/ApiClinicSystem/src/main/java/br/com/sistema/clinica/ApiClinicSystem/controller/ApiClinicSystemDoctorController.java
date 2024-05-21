package br.com.sistema.clinica.ApiClinicSystem.controller;

import br.com.sistema.clinica.ApiClinicSystem.doctor.ApiClinicSystemDoctor;
import br.com.sistema.clinica.ApiClinicSystem.doctor.DoctorRegistrationData;
import br.com.sistema.clinica.ApiClinicSystem.doctor.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class ApiClinicSystemDoctorController {

    @Autowired
    private IDoctorRepository iDoctorRepository;

    @PostMapping
    public void register(@RequestBody DoctorRegistrationData doctorRegistrationData) {
        iDoctorRepository.save(new ApiClinicSystemDoctor(doctorRegistrationData));
    }
}
