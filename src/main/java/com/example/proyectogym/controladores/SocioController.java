package com.example.proyectogym.controladores;


import com.example.proyectogym.dto.*;
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
    public MensajeDTO eliminar(@RequestParam Integer id) {
        return socioService.eliminarPorId(id);
    }

    @PostMapping("/guardar")
    public Socio guardarSocioConAbono(@RequestBody SocioAbonoDTO dto) {
        return socioService.guardarSocioConAbono(dto);
    }

    @PostMapping("/abono/renovar")
    public MensajeAbonoSocioDTO renovarAbono(@RequestParam Integer idSocio) {
        RenovarAbonoVigenteDTO dto = new RenovarAbonoVigenteDTO();
        dto.setIdSocio(idSocio);

        MensajeAbonoSocioDTO response = abonoService.renovarAbonoVigente(dto);

        return response;
    }


    @GetMapping("/asistencia")
    public MensajeDTO registrarAsistencia(@RequestParam Integer socioId) {
        AsistenciaDto dto = new AsistenciaDto();
        dto.setSocioId(socioId);
        return asistenciaService.totalAsistencia(dto);
    }

    @GetMapping("gasto")
    public MensajeDTO totalGasto(@RequestParam Integer socioId) {
        return socioService.totalGasto(socioId);
    }


}


