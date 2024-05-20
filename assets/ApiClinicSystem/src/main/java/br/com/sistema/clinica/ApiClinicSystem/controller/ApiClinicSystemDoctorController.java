package br.com.sistema.clinica.ApiClinicSystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class ApiClinicSystemDoctorController {

    @PostMapping
    public void register(@RequestBody String json) {
        System.out.println(json);
    }
}
