package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepositorio extends JpaRepository<Monitor,Integer> {
}
