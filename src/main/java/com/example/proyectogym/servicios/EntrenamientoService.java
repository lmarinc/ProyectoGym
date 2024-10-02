package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.Entrenamiento;
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

    /**
     * Método para eliminar un entrenamiento por su id
     * @param id
     */
    public void eliminar(Integer id) {
        entrenamientoRepositorio.deleteById(id);
    }

    /**
     * Método para eliminar un entrenamiento
     * @param entrenamiento
     */

    public void eliminar(Entrenamiento entrenamiento) {
        entrenamientoRepositorio.delete(entrenamiento);
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
