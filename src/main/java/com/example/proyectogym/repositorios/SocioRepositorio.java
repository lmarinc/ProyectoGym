package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepositorio extends JpaRepository<Socio,Integer> {
}
