package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.repositorios.AbonoSocioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbonoSocioService {

    private AbonoSocioRepositorio abonoSocioRepositorio;

    /**
     * Método para buscar un abono por su id
     * @param id
     * @return
     */

    public AbonoSocio getAbonoSocioPorId(Integer id){
        return abonoSocioRepositorio.findById(id).orElse(null);
    }

    /**
     * Método para guardar un abono
     * @param abonoSocio
     * @return
     */


    public AbonoSocio guardar(AbonoSocio abonoSocio) {
        return abonoSocioRepositorio.save(abonoSocio);
    }
    /**
     * Método para eliminar un abono
     * @param id
     */
    public void eliminar(Integer id) {
        abonoSocioRepositorio.deleteById(id);
    }

    /**
     * Método para eliminar un abono
     * @param abonoSocio
     */
    public void eliminar(AbonoSocio abonoSocio) {
        abonoSocioRepositorio.delete(abonoSocio);
    }

    /**
     * Método que devuelve una lista de todos los abonos
     * @return
     */
    public List<AbonoSocio> getAll(){
        return abonoSocioRepositorio.findAll();
    }

}
