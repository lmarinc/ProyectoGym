package com.example.proyectogym.servicios;


import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbonoService {


    private AbonoRepositorio abonoRepositorio;

    /**
     * Método que devuelve una lista de abonos por duración
     * @param tipoAbono
     * @return
     */
    public List<Abono> getAbonoPorTipo(TipoAbono tipoAbono){
        List<Abono> abonos = abonoRepositorio.findAllByTipoAbonoEquals(tipoAbono);
        return abonos;

    }

    /**
     * Método para buscar un abono por su id
     * @param id
     * @return
     */
    public Abono getAbonoPorId(Integer id){
        Abono abono = abonoRepositorio.findById(id).orElse(null);
        return abono;

    }
    /**
     * Método para buscar un abono por su nombre
     * @param tipoAbono
     * @return
     */
    public Abono getAbonoPorNombre(TipoAbono tipoAbono){
        Abono abono = abonoRepositorio.findAbonoByTipoAbonoEquals(tipoAbono);
        return abono;
    }
    /**
     * Método que devuelve una lista de todos los abonos
     * @return
     */
    public List<Abono> getAll(){
        return abonoRepositorio.findAll();
    }

    /**
     * Metodo para crear un abono
     */
    public Abono guardar(Abono abono) {
        return abonoRepositorio.save(abono);

    }
    /**
     * Metodo para eliminar un abono
     */
    public String eliminarPorId(Integer id) {
        Abono abono = abonoRepositorio.findById(id).orElse(null);

        if (abono == null) {
            return "Abono no encontrado";
        }

        try {
            abonoRepositorio.delete(abono);
            return "Abono eliminado";
        } catch (Exception e) {
            return "Error al eliminar el abono";
        }
    }
    public void eliminar(Abono abono) {
        abonoRepositorio.delete(abono);
    }



}
