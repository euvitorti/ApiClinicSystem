package br.com.sistema.clinica.ApiClinicSystem.repository.doctor;

import br.com.sistema.clinica.ApiClinicSystem.models.doctor.SpecialtyEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

// A ANOTAÇÃO @DataJpaTest, é utilizada para testar uma interface Repository
// A ANOTAÇÃO AutoConfigureTestDatabase.Replace.NONE, INFORMA AO SPRING,
// PARA NÃO CRIAR UM BANCO EM MÉMORIA E USAR O BANCO REAL
// A ANOTAÇÃO @ActiveProfiles, INFORMA AO SPRING, QUE DEVE USAR O application.properties QUE TENHA A "STRING"
// DECLARADA NO NOME. POIS O BANCO DE DADOS DE PRODUÇÃO, JÁ TEM CADASTROS, E ISSO PODERIA INTERFERIR EM ALGO


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class IDoctorRepositoryTest {

    @Autowired
    private IDoctorRepository iDoctorRepository;

//    @Autowired
//    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("SHOULD RETURN NULL WHEN THE ONLY REGISTERED DOCTOR IS NOT AVAILABLE ON THE DATE")
    void chooseFreeDoctorOnTheDateScenario1() {

        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        // O BANCO DEVE TER ALGUNS DADOS JÁ CADASTRADOS PARA O TESTE
        // ESTAREI USANDO O BANCO JÁ CRIADO, PORÉM POR MOTIVOS DE SEGURANÇA
        // CRIE UM SEPARADO COM OS DADOS, PARA FINS DE TESTE

        var doctorFree = iDoctorRepository.ChooseFreeDoctorOnTheDate(SpecialtyEnum.CARDIOLOGIA, nextMondayAt10);

        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("SHOULD RETURN DOCTOR WHEN THE DOCTOR IS AVAILABLE ON THE DATE")
    void chooseFreeDoctorOnTheDateScenario2() {

        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctorFree = iDoctorRepository.ChooseFreeDoctorOnTheDate(SpecialtyEnum.CARDIOLOGIA, nextMondayAt10);

        assertThat(doctorFree).isEqualTo(doctorFree);
    }

}