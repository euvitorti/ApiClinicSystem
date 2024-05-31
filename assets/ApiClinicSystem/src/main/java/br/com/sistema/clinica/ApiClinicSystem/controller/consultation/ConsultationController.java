package br.com.sistema.clinica.ApiClinicSystem.controller.consultation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ConsultationDetailsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultationController {

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleAppointmentDTO scheduleAppointmentDTO) {
        return ResponseEntity.ok(new ConsultationDetailsDTO(null,null,null,null));
    }
}
