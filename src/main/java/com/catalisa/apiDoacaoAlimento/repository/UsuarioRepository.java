package com.catalisa.apiDoacaoAlimento.repository;

import com.catalisa.apiDoacaoAlimento.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuarios,Long> {

    UserDetails findByLogin(String login);
}
