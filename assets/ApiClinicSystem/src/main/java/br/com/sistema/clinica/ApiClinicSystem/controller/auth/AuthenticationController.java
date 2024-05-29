package br.com.sistema.clinica.ApiClinicSystem.controller.auth;

import br.com.sistema.clinica.ApiClinicSystem.dto.authDto.AuthenticationDTO;
import br.com.sistema.clinica.ApiClinicSystem.infra.security.TokenJwt;
import br.com.sistema.clinica.ApiClinicSystem.models.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    // ESTA É CLASSE QUE DISPARA O PROCESSO DE AUTENTICAÇÃO
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenJwt tokenJwt;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var token = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(token);

        // PASSANDO O USUÁRIO COMO PARÂMETRO
        return ResponseEntity.ok(tokenJwt.generateToken((User) auth.getPrincipal()));
    }
}
