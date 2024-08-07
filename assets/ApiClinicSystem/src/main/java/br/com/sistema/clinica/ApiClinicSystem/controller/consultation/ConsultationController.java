package br.com.sistema.clinica.ApiClinicSystem.controller.consultation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.DataCancelConsultationDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.services.ConsultationsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// CLASSE CONTROLLER NÃO DEVE TER REGRAS DE NOGÓCIO DA APLICAÇÃO

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private ConsultationsService consultationsService;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleAppointmentDTO scheduleAppointmentDTO) {
        var consultationDto = consultationsService.schedule(scheduleAppointmentDTO);
        return ResponseEntity.ok(consultationDto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DataCancelConsultationDTO dataCancelConsultationDTO) {
        consultationsService.cancel(dataCancelConsultationDTO);
        return ResponseEntity.noContent().build();
    }
}
