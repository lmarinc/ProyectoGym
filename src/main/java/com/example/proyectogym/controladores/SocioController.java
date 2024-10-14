package com.example.proyectogym.controladores;


import com.example.proyectogym.dto.AsistenciaDto;
import com.example.proyectogym.dto.RenovarAbonoSocioDTO;
import com.example.proyectogym.dto.RenovarAbonoVigenteDTO;
import com.example.proyectogym.dto.SocioAbonoDTO;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import com.example.proyectogym.servicios.AbonoService;
import com.example.proyectogym.servicios.AbonoSocioService;
import com.example.proyectogym.servicios.AsistenciaService;
import com.example.proyectogym.servicios.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/socio")

public class SocioController {

    @Autowired
    private SocioService socioService;

    @Autowired
    private AbonoRepositorio abonoRepository;

    @Autowired
    private AbonoService abonoService;

    @Autowired
    private AbonoSocioService abonoSocioService;

    @Autowired
    private AsistenciaService asistenciaService;



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

    @PostMapping("/editar")
    public Socio guardar(@RequestBody Socio socio) {
        Socio socioGuardado = socioService.guardar(socio);
        return socioGuardado;
    }

    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        return socioService.eliminarPorId(id);
    }

    @PostMapping("/guardar")
    public Socio guardarSocioConAbono(@RequestBody SocioAbonoDTO dto) {
        return socioService.guardarSocioConAbono(dto);
    }

    @PostMapping("/abono/renovar")
    public Object renovarAbono(@RequestParam Integer idSocio) {
        // Crear el DTO a partir del parámetro
        RenovarAbonoVigenteDTO dto = new RenovarAbonoVigenteDTO();
        dto.setIdSocio(idSocio);

        AbonoSocio nuevoAbonoSocio = abonoService.renovarAbonoVigente(dto);

        if (nuevoAbonoSocio == null) {
            // Retornar un mensaje de error como String
            return "Error: El socio con ID " + idSocio + " no tiene abonos previos o no se pudo renovar el abono.";
        }

        // Si el abono fue renovado correctamente, retornamos el objeto AbonoSocio
        return nuevoAbonoSocio;
    }


    @GetMapping("/asistencia")
    public String registrarAsistencia(@RequestParam Integer socioId) {
        AsistenciaDto dto = new AsistenciaDto();
        dto.setSocioId(socioId);
        return asistenciaService.totalAsistencia(dto);
    }

    @GetMapping("gasto")
    public String totalGasto(@RequestParam Integer socioId) {
        return socioService.totalGasto(socioId);
    }


}


