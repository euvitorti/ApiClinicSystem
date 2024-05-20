package br.com.sistema.clinica.ApiClinicSystem.address;

public record Address(
        String street,
        String neighborhood,
        String cep,
        String city,
        String uf,
        String number,
        String complement) {
}
