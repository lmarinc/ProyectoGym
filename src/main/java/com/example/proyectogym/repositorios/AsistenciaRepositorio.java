package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepositorio extends JpaRepository<Asistencia,Integer> {
}
