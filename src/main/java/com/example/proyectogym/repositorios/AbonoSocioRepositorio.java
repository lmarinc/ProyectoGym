package com.example.proyectogym.repositorios;


import com.example.proyectogym.modelos.AbonoSocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonoSocioRepositorio extends JpaRepository<AbonoSocio,Integer> {
}
