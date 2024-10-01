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

    public void eliminar(Integer id) {
        entrenamientoRepositorio.deleteById(id);
    }

    public void eliminar(Entrenamiento entrenamiento) {
        entrenamientoRepositorio.delete(entrenamiento);
    }

    public Entrenamiento guardar(Entrenamiento entrenamiento) {
        return entrenamientoRepositorio.save(entrenamiento);
    }

    public List<Entrenamiento> getAll() {
        return entrenamientoRepositorio.findAll();
    }
}
