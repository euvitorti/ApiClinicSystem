package br.com.sistema.clinica.ApiClinicSystem.models.address;

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
    private String complemento;
    private String cidade;
    private String uf;

    public Address(DataAddress address) {
        this.logradouro = address.street();
        this.bairro = address.neighborhood();
        this.cep = address.cep();
        this.cidade = address.city();
        this.uf = address.uf();
        this.numero = address.number();
        this.complemento = address.complement();
    }

    public void updateAddress(DataAddress dataAddress) {

        if(dataAddress.street() != null) {
            this.logradouro = dataAddress.street();
        }

        if(dataAddress.neighborhood() != null) {
            this.bairro = dataAddress.neighborhood();
        }

        if(dataAddress.cep() != null) {
            this.cep = dataAddress.cep();
        }

        if(dataAddress.city() != null) {
            this.cidade = dataAddress.city();
        }

        if(dataAddress.uf() != null) {
            this.uf = dataAddress.uf();
        }

        if(dataAddress.number() != null) {
            this.numero = dataAddress.number();
        }

        if(dataAddress.complement() != null) {
            this.complemento = dataAddress.complement();
        }

    }
}
