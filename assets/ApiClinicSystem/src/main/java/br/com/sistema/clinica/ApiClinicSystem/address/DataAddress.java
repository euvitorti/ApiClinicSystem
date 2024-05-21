package br.com.sistema.clinica.ApiClinicSystem.address;

public record DataAddress(  String street,
                            String neighborhood,
                            String cep,
                            String city,
                            String uf,
                            String number,
                            String complement) { }
