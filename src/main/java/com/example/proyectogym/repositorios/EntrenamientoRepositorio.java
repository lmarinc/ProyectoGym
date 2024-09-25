package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenamientoRepositorio extends JpaRepository<Entrenamiento,Integer> {
}
