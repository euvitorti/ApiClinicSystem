package br.com.sistema.clinica.ApiClinicSystem.doctor;

import br.com.sistema.clinica.ApiClinicSystem.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ApiClinicSystemDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private SpecialtyEnum especialidade;

    @Embedded
    private Address endereco;

    public ApiClinicSystemDoctor(DoctorRegistrationData doctorRegistrationData) {
        this.nome = doctorRegistrationData.name();
        this.email = doctorRegistrationData.email();
        this.telefone = doctorRegistrationData.phone();
        this.crm = doctorRegistrationData.crm();
        this.especialidade = doctorRegistrationData.specialty();
        this.endereco = new Address(doctorRegistrationData.address());
    }
}
