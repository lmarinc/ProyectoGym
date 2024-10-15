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
     * Metodo para encontrar la ultima entrada sin salida de un socio
     * @param socioId
     * @return
     */
    @Query("SELECT a FROM Asistencia a WHERE a.socio.id = :socioId AND a.horaSalida IS NULL ORDER BY a.fecha DESC, a.horaEntrada DESC")
    Asistencia findLastEntradaWithoutSalida(@Param("socioId") Integer socioId);

    /**
     * Metodo para encontrar la ultima entrada de un socio
     * @param socioId
     * @return
     */
        boolean existsBySocioIdAndHoraSalidaIsNull(Integer socioId);

    /**
     * Metodo para encontrar todas las asistencias de un socio
     * @param socioId
     * @return
     */
    List<Asistencia> findBySocioIdAndHoraSalidaIsNotNull(Integer socioId);

    /**
     * Metodo para encontrar todas las asistencias de un socio
     * @param socioId
     * @return
     */
    @Query("SELECT COUNT(DISTINCT a.fecha) FROM Asistencia a WHERE a.socio.id = :socioId AND a.horaSalida IS NOT NULL")
    int obtenerTotalDiasAsistencia(@Param("socioId") Integer socioId);

    /**
     * Metodo para obtener el total de horas de asistencia de un socio
     * @param socioId
     * @return
     */
    @Query("SELECT SUM(TIMESTAMPDIFF(HOUR, a.horaEntrada, a.horaSalida)) FROM Asistencia a WHERE a.socio.id = :socioId AND a.horaEntrada IS NOT NULL AND a.horaSalida IS NOT NULL")
    Integer obtenerTotalHorasAsistencia(@Param("socioId") Integer socioId);
}
