package com.example.proyectogym.servicios;

import com.example.proyectogym.dto.AsistenciaDto;
import com.example.proyectogym.dto.MensajeDTO;
import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AsistenciaRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class AsistenciaService {

    private AsistenciaRepositorio asistenciaRepositorio;
    private SocioRepositorio socioRepositorio;


    /**
     * Método para buscar una asistencia por su id
     * @param id
     */

    public Asistencia findById(Integer id) {
        return asistenciaRepositorio.findById(id).orElse(null);
    }

    /**
     * Método que devuelve una lista de todas las asistencias
     * @return
     */
    public List<Asistencia> getAll() {
        return asistenciaRepositorio.findAll();
    }

    /**
     * Metodo que guarda una entrada de asistencia por Dto
     * @param asistenciaDto
     * @return
     */

    public String entradaAsistencia(AsistenciaDto asistenciaDto) {
        Integer socioId = asistenciaDto.getSocioId();

        if (asistenciaRepositorio.existsBySocioIdAndHoraSalidaIsNull(socioId)) {
            return "El socio con ID " + socioId + " ya tiene una entrada sin salida registrada.";
        }

        Asistencia asistencia = new Asistencia();
        LocalDate fechaEntrada = LocalDate.now();
        Time horaEntrada = Time.valueOf(LocalTime.now());

        try {
            asistencia.setSocio(socioRepositorio.findById(socioId).orElseThrow(
                    () -> new RuntimeException("Socio no encontrado con ID: " + socioId)
            ));

            asistencia.setFecha(fechaEntrada);
            asistencia.setHoraEntrada(horaEntrada);
            asistencia.setHoraSalida(null);

            asistenciaRepositorio.save(asistencia);
            return "Entrada registrada con éxito para el socio con ID: " + socioId;
        } catch (Exception e) {
            return "Error al registrar la entrada: " + e.getMessage();
        }
    }

    public String salidaAsistencia(AsistenciaDto asistenciaDto) {
        Integer socioId = asistenciaDto.getSocioId();

        try {
            Asistencia ultimaEntrada = asistenciaRepositorio.findLastEntradaWithoutSalida(socioId);

            // Verificar si se encontró una entrada activa
            if (ultimaEntrada == null) {
                return "No hay entradas activas para el socio con ID: " + socioId;
            }

            Time horaSalida = Time.valueOf(LocalTime.now());
            ultimaEntrada.setHoraSalida(horaSalida);

            asistenciaRepositorio.save(ultimaEntrada);
            return "Salida registrada con éxito para el socio con ID: " + socioId;
        } catch (Exception e) {
            return "Error al registrar la salida: " + e.getMessage();
        }
    }

    public MensajeDTO totalAsistencia(AsistenciaDto asistenciaDto) {
        Integer socioId = asistenciaDto.getSocioId();

        try {
            int totalDias = asistenciaRepositorio.obtenerTotalDiasAsistencia(socioId);

            Integer totalHoras = asistenciaRepositorio.obtenerTotalHorasAsistencia(socioId);

            if (totalDias == 0 || totalHoras == null) {
                return new MensajeDTO("El socio con ID " + socioId + " no tiene asistencias registradas.");
            }

            String mensaje = "El socio con ID " + socioId + " ha asistido un total de " + totalDias + " días, acumulando " + totalHoras + " horas en el gimnasio.";

            return new MensajeDTO(mensaje);
        } catch (Exception e) {
            String mensajeError = "Error al calcular la asistencia: " + e.getMessage();
            return new MensajeDTO(mensajeError);
        }
    }

}



