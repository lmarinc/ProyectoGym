package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SocioService {

    private SocioRepositorio socioRepositorio;

    /**
     * Método para buscar un socio por su id
     * @param id
     */

    public void eliminar(Integer id) {
        socioRepositorio.deleteById(id);
    }

    /**
     * Método para eliminar un socio
     * @param socio
     */

    public String eliminarPorId(Integer id) {
        Socio socio = socioRepositorio.findById(id).orElse(null);

        if (socio == null) {
            return "Socio no encontrado";
        }

        try {
            socioRepositorio.delete(socio);
            return "Socio eliminado";
        } catch (Exception e) {
            return "Error al eliminar el socio";
        }
    }

    /**
     * Método para guardar un socio
     * @param socio
     * @return
     */

    public Socio guardar(Socio socio) {
        return socioRepositorio.save(socio);
    }

    /**
     * Método que devuelve una lista de todos los socios
     * @return
     */

    public List<Socio> getAll() {
        return socioRepositorio.findAll();
    }

    /**
     * Método para buscar un socio por su id
     * @param id
     * @return
     */

    public Socio getSocioPorId(Integer id){
        Socio socio = socioRepositorio.findById(id).orElse(null);
        return socio;
    }



}
