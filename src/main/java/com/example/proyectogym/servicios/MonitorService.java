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

    /**
     * Método para buscar un monitor por su id
     * @param id
     */
    public void eliminar(Integer id) {
        monitorRepositorio.deleteById(id);
    }

    /**
     * Método para eliminar un monitor
     * @param monitor
     */
    public void eliminar(Monitor monitor) {
        monitorRepositorio.delete(monitor);
    }

    /**
     * Método para buscar un monitor por su id
     * @param id
     */

    public Monitor findById(Integer id) {
        return monitorRepositorio.findById(id).orElse(null);
    }



    /**
     * Método para guardar un monitor
     * @param monitor
     * @return
     */

    public Monitor guardar(Monitor monitor) {
        return monitorRepositorio.save(monitor);
    }

    /**
     * Método que devuelve una lista de todos los monitores
     * @return
     */

    public List<Monitor> getAll() {
        return monitorRepositorio.findAll();
    }
}
