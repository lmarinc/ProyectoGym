package com.example.proyectogym.servicios;


import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.repositorios.AbonoRepositorio;
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
    public Abono getAbonoPorId(Integer id){
        Abono abono = abonoRepositorio.findById(id).orElse(null);
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
    public void eliminar(Integer id) {
        abonoRepositorio.deleteById(id);
    }
    public void eliminar(Abono abono) {
        abonoRepositorio.delete(abono);
    }

}
