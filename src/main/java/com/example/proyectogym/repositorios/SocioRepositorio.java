package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepositorio extends JpaRepository<Socio,Integer> {

    //JPA Interface

    /**
     * Busca todos los socios por su Dni
     * @param dni
     * @return
     */
    List<Socio> findAllByDniEquals(String dni);


}
