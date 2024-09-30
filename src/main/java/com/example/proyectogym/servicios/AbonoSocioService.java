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

    public AbonoSocio getAbonoSocioPorId(Integer id){
        return abonoSocioRepositorio.findById(id).orElse(null);
    }

    public AbonoSocio guardar(AbonoSocio abonoSocio) {
        return abonoSocioRepositorio.save(abonoSocio);
    }

    public void eliminar(Integer id) {
        abonoSocioRepositorio.deleteById(id);
    }

    public void eliminar(AbonoSocio abonoSocio) {
        abonoSocioRepositorio.delete(abonoSocio);
    }

    public List<AbonoSocio> getAll(){
        return abonoSocioRepositorio.findAll();
    }

}
