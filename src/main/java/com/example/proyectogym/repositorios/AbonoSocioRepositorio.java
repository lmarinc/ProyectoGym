package com.example.proyectogym.repositorios;


import com.example.proyectogym.modelos.AbonoSocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AbonoSocioRepositorio extends JpaRepository<AbonoSocio,Integer> {

    @Query("SELECT SUM(a.precio) FROM AbonoSocio a WHERE a.socio.id = :socioId")
    Double obtenerTotalGastoPorSocio(@Param("socioId") Integer socioId);

    List<AbonoSocio> findBySocioId(Integer socioId);

    AbonoSocio findFirstBySocioIdOrderByFechaFinDesc(Integer socioId);

    boolean existsBySocioIdAndFechaFinAfter(Integer socioId, LocalDate fechaActual);


}
