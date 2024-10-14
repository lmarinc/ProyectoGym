package com.example.proyectogym.repositorios;

import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.modelos.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaRepositorio extends JpaRepository<Asistencia,Integer> {

    /**
     * Metodo para encontrar la ultima asistencia de un socio
     * @param socio
     * @return
     */
    @Query("SELECT a FROM Asistencia a WHERE a.socio = :socio ORDER BY a.fecha DESC")
    Optional<Asistencia> findLatestAsistenciaBySocio(@Param("socio") Socio socio);


    /**
     * Metodo para encontrar la ultima entrada sin salida de un socio
     * @param socioId
     * @return
     */
    @Query("SELECT a FROM Asistencia a WHERE a.socio.id = :socioId AND a.horaSalida IS NULL ORDER BY a.fecha DESC, a.horaEntrada DESC")
    Asistencia findLastEntradaWithoutSalida(@Param("socioId") Integer socioId);

    boolean existsBySocioIdAndHoraSalidaIsNull(Integer socioId);

    List<Asistencia> findBySocioIdAndHoraSalidaIsNotNull(Integer socioId);
}
