package br.com.sistema.clinica.ApiClinicSystem.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complement;
    private String cidade;
    private String uf;

    public Address(DataAddress address) {
        this.logradouro = address.street();
        this.bairro = address.neighborhood();
        this.cep = address.cep();
        this.cidade = address.city();
        this.uf = address.uf();
        this.numero = address.number();
        this.complement = address.complement();
    }
}
