package br.com.sistema.clinica.ApiClinicSystem.paciente;

import br.com.sistema.clinica.ApiClinicSystem.address.Address;
import br.com.sistema.clinica.ApiClinicSystem.dto.PacienteDTO;
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

    public Paciente(@Valid PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.name();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.phone();
        this.cpf = pacienteDTO.cpf();
        this.endereco = new Address(pacienteDTO.address());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
