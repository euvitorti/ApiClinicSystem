package br.com.sistema.clinica.ApiClinicSystem.controller.consultation;

import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ConsultationDetailsDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.consultationDto.ScheduleAppointmentDTO;
import br.com.sistema.clinica.ApiClinicSystem.models.doctor.SpecialtyEnum;
import br.com.sistema.clinica.ApiClinicSystem.services.ConsultationsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// USA A ANOTAÇÃO @SpringBootTest, PARA TESTAR CLASSES CONTROLLERS

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    // UNIT TEST
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentDTO> json;

    @Autowired
    private JacksonTester<ConsultationDetailsDTO> jsonResponse;

    // O SPRING NÃO VAI MAIS INJETAR
    @MockBean
    private ConsultationsService consultationsService;

    // A ANOTAÇÃO @WithMockUser, FAZ COM QUE O SPRING, PULE A ETAPA DE VERIFICAÇÃO DE USUÁRIO
    // ELE CRIA UM USUÁRIO JÁ LOGADO

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    void scheduleScenario1() throws Exception {
        // PERFORM AN API REQUEST
        // INSIDE THE PARENTHESES, STATE WHICH METHOD YOU WANT TO TEST

        var response = mockMvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    @WithMockUser
    void scheduleScenario2() throws Exception {

        // TEST WITH JSON

        var data = LocalDateTime.now().plusHours(1);
        var specialty = SpecialtyEnum.GINECOLOGIA;

        var consultationDetails = new ConsultationDetailsDTO(null, 5L,1L, data);

        // MOCKITO
        // NÃO IMPORTA QUAL SEJA O PARÂMETROS, DEVOLVERÁ ESTES DADOS
        when(consultationsService.schedule(
                any()))
                .thenReturn(consultationDetails);

        var response = mockMvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json.write(
                                        new ScheduleAppointmentDTO(5L,1L, data, specialty
                                        )).getJson()
                                ))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = jsonResponse.write(
                consultationDetails
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}