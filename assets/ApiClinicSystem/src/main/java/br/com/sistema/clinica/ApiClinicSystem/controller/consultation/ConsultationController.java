package br.com.sistema.clinica.ApiClinicSystem.controller.consultation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ConsultationDetailsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.DataCancelConsultationDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.services.consultations.ConsultationsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// CLASSE CONTROLLER NÃO DEVE TER REGRAS DE NOGÓCIO DA APLICAÇÃO

@RestController
@RequestMapping("/consultas")
public class ConsultationController {

    @Autowired
    private ConsultationsService consultationsService;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleAppointmentDTO scheduleAppointmentDTO) {
        consultationsService.schedule(scheduleAppointmentDTO);
        return ResponseEntity.ok(new ConsultationDetailsDTO(null,null,null,null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DataCancelConsultationDTO dataCancelConsultationDTO) {
        consultationsService.cancel(dataCancelConsultationDTO);
        return ResponseEntity.noContent().build();
    }
}
