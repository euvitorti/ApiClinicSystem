package br.com.sistema.clinica.ApiClinicSystem.models.doctor;

import br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto.DoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.doctorDto.UpdateDoctorDTO;
import br.com.sistema.clinica.ApiClinicSystem.models.address.Address;
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

    @Embedded
    private Address endereco;

    private Boolean ativo;

    public Doctor(DoctorDTO doctorDTO) {
        this.nome = doctorDTO.name();
        this.email = doctorDTO.email();
        this.telefone = doctorDTO.phone();
        this.crm = doctorDTO.crm();
        this.especialidade = doctorDTO.specialty();
        this.endereco = new Address(doctorDTO.address());
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCrm() {
        return crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public Address getEndereco() {
        return endereco;
    }

    public SpecialtyEnum getEspecialidade() {
        return especialidade;
    }

    public void updateDoctor(UpdateDoctorDTO updateDoctorDTO) {

        if(updateDoctorDTO.name() != null) {
            this.nome = updateDoctorDTO.name();
        }

        if(updateDoctorDTO.phone() != null) {
        this.telefone = updateDoctorDTO.phone();
        }

        if(updateDoctorDTO.dataAddress() != null) {
            this.endereco.updateAddress(updateDoctorDTO.dataAddress());
        }
    }

    public void logicalExclusion() {
        this.ativo = false;
    }
}
