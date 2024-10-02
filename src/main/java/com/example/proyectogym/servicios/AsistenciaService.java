package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.repositorios.AsistenciaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AsistenciaService {

    private AsistenciaRepositorio asistenciaRepositorio;


    /**
     * Método para buscar una asistencia por su id
     * @param id
     */

    public Asistencia findById(Integer id) {
        return asistenciaRepositorio.findById(id).orElse(null);
    }

    /**
     * Método para eliminar una asistencia por su id
     * @param id
     */
    public void eliminar(Integer id) {
        asistenciaRepositorio.deleteById(id);
    }

    /**
     * Método para eliminar una asistencia
     * @param asistencia
     */
    public void eliminar(Asistencia asistencia) {
        asistenciaRepositorio.delete(asistencia);
    }

    /**
     * Método para guardar una asistencia
     * @param asistencia
     * @return
     */
    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepositorio.save(asistencia);
    }

    /**
     * Método que devuelve una lista de todas las asistencias
     * @return
     */
    public List<Asistencia> getAll() {
        return asistenciaRepositorio.findAll();
    }
}
