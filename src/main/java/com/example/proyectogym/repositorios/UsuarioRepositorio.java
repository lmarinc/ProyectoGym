package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UsuarioRepositorio extends UserDetailsService {

    Optional<Usuario> findByUsername(String username);
}
