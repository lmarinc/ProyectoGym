package com.example.proyectogym.repositorios;


import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonoRepositorio extends JpaRepository<Abono,Integer> {

    List<Abono> findAllByTipoAbonoEquals(TipoAbono tipoAbono);



}
