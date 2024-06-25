package br.com.sistema.clinica.ApiClinicSystem.repository.doctor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

// A ANOTAÇÃO @DataJpaTest, é utilizada para testar uma interface Repository
// A ANOTAÇÃO AutoConfigureTestDatabase.Replace.NONE, INFORMA AO SPRING,
// PARA NÃO CRIAR UM BANCO EM MÉMORIA E USAR O BANCO REAL
// A ANOTAÇÃO @ActiveProfiles, INFORMA AO SPRING, QUE DEVE USAR O application.properties QUE TENHA A "STRING"
// DECLARADA NO NOME. POIS O BANCO DE DADOS DE PRODUÇÃO, JÁ TEM CADASTROS, E ISSO PODERIA INTERFERIR EM ALGO


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IDoctorRepositoryTest {

    @Test
    void chooseFreeDoctorOnTheDate() {
    }
}