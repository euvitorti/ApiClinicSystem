package br.com.sistema.clinica.ApiClinicSystem.repository.user;

import br.com.sistema.clinica.ApiClinicSystem.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);
}
