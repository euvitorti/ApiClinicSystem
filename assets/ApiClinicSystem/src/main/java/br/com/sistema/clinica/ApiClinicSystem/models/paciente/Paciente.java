package br.com.sistema.clinica.ApiClinicSystem.models.paciente;

import br.com.sistema.clinica.ApiClinicSystem.models.address.Address;
import br.com.sistema.clinica.ApiClinicSystem.dto.PacienteDTO;
import br.com.sistema.clinica.ApiClinicSystem.dto.UpdatePacienteDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Address endereco;

    private Boolean ativo;

    public Paciente(@Valid PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.name();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.phone();
        this.cpf = pacienteDTO.cpf();
        this.endereco = new Address(pacienteDTO.address());
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Address getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public Long getId() {
        return id;
    }

    public void updateInformationPaciente(UpdatePacienteDTO updatePacienteDTO) {

        if(updatePacienteDTO.name() != null) {
            this.nome = updatePacienteDTO.name();
        }

        if(updatePacienteDTO.phone() != null) {
            this.telefone = updatePacienteDTO.phone();
        }

        if(updatePacienteDTO.dataAddress() != null) {
            this.endereco.updateAddress(updatePacienteDTO.dataAddress());
        }
    }

    public void logicalExclusion() {
        this.ativo = false;
    }
}
