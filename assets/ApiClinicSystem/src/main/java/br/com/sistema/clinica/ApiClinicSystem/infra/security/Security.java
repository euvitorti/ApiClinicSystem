package br.com.sistema.clinica.ApiClinicSystem.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

// Usa a seguinte anotação, para informar ao spring que vamos personalizar a configuração de segurança
@EnableWebSecurity
public class Security {

    // Objeto que é usado para configurar processos de autenticação e autorização
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Para desabilitar a proteção ataques do tipo CSRF (Cross-Site Request Forgery)
        // Motivo: O TOKEN já vem habilitado para esse tipo de ataque, logo seria redundante

        return httpSecurity.csrf(c -> c.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
