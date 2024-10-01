package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.Monitor;
import com.example.proyectogym.repositorios.MonitorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonitorService {

    private MonitorRepositorio monitorRepositorio;

    public void eliminar(Integer id) {
        monitorRepositorio.deleteById(id);
    }

    public void eliminar(Monitor monitor) {
        monitorRepositorio.delete(monitor);
    }

    public Monitor guardar(Monitor monitor) {
        return monitorRepositorio.save(monitor);
    }

    public List<Monitor> getAll() {
        return monitorRepositorio.findAll();
    }
}
