package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.Entrenamiento;
import com.example.proyectogym.modelos.Monitor;
import com.example.proyectogym.repositorios.EntrenamientoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntrenamientoService {

    private EntrenamientoRepositorio entrenamientoRepositorio;


    /**
     * Método para buscar un entrenamiento por su id
     * @param id
     */

    public Entrenamiento findById(Integer id) {
        return entrenamientoRepositorio.findById(id).orElse(null);


    }

    public String eliminarPorId(Integer id) {
        Entrenamiento entrenamiento = entrenamientoRepositorio.findById(id).orElse(null);

        if (entrenamiento == null) {
            return "Entrenamiento no encontrado";
        }

        try {
            entrenamientoRepositorio.delete(entrenamiento);
            return "Entrenamiento eliminado";
        } catch (Exception e) {
            return "Error al eliminar el entrenamiento";
        }
    }

    /**
     * Método para guardar un entrenamiento
     * @param entrenamiento
     * @return
     */

    public Entrenamiento guardar(Entrenamiento entrenamiento) {
        return entrenamientoRepositorio.save(entrenamiento);
    }

    /**
     * Método que devuelve una lista de todos los entrenamientos
     * @return
     */

    public List<Entrenamiento> getAll() {
        return entrenamientoRepositorio.findAll();
    }

}
