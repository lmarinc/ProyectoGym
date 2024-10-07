package com.example.proyectogym.controladores;


import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.servicios.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socio")

public class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping("/listar")
    public List<Socio> getAllSocios() {
        List<Socio> socios = socioService.getAll();

        return socios;
    }
    @GetMapping()
    public Socio getById(@RequestParam Integer id) {
        Socio socio = socioService.getSocioPorId(id);
        return socio;
    }

    @GetMapping("/{id}")
    public Socio getbyIdPath(@PathVariable Integer id) {
        Socio socio = socioService.getSocioPorId(id);
        return socio;
    }

    @PostMapping()
    public Socio guardar(@RequestBody Socio socio) {
        Socio socioGuardado = socioService.guardar(socio);
        return socioGuardado;
    }

    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return socioService.eliminarPorId(id);
    }

}
