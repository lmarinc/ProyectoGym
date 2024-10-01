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

    public void eliminar(Integer id) {
        asistenciaRepositorio.deleteById(id);
    }

    public void eliminar(Asistencia asistencia) {
        asistenciaRepositorio.delete(asistencia);
    }

    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepositorio.save(asistencia);
    }

    public List<Asistencia> getAll() {
        return asistenciaRepositorio.findAll();
    }
}
