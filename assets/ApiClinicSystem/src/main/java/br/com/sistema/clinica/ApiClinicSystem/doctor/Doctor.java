package br.com.sistema.clinica.ApiClinicSystem.doctor;

import br.com.sistema.clinica.ApiClinicSystem.address.Address;
import br.com.sistema.clinica.ApiClinicSystem.dto.DoctorDTO;
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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private SpecialtyEnum especialidade;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCrm() {
        return crm;
    }

    public SpecialtyEnum getEspecialidade() {
        return especialidade;
    }

    public Address getEndereco() {
        return endereco;
    }

    @Embedded
    private Address endereco;

    public Doctor(DoctorDTO doctorDTO) {
        this.nome = doctorDTO.name();
        this.email = doctorDTO.email();
        this.telefone = doctorDTO.phone();
        this.crm = doctorDTO.crm();
        this.especialidade = doctorDTO.specialty();
        this.endereco = new Address(doctorDTO.address());
    }
}
