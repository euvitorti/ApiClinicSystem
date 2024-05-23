package br.com.sistema.clinica.ApiClinicSystem.paciente;

import br.com.sistema.clinica.ApiClinicSystem.address.Address;
import br.com.sistema.clinica.ApiClinicSystem.dto.PacienteDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;

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
}
