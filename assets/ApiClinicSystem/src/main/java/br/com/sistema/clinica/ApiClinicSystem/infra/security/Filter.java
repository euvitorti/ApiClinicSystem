package br.com.sistema.clinica.ApiClinicSystem.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// O @Component é utilizado para que o Spring carregue uma classe/componente genérico

@Component
public class Filter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

            // NECESSÁRIO PARA CHAMAR OS PRÓXIMOS FILTROS NA APLICAÇÃO
            filterChain.doFilter(request, response);

    }
}
