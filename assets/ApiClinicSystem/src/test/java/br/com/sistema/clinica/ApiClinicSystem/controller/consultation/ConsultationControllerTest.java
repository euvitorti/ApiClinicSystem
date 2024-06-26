package br.com.sistema.clinica.ApiClinicSystem.controller.consultation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// USA A ANOTAÇÃO @SpringBootTest, PARA TESTAR CLASSES CONTROLLERS

@SpringBootTest
@AutoConfigureMockMvc
class ConsultationControllerTest {

    // UNIT TEST
    @Autowired
    private MockMvc mockMvc;

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
}