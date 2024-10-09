package com.example.proyectogym.controladores;

import com.example.proyectogym.dto.AsistenciaDto;
import com.example.proyectogym.dto.RenovarAbonoSocioDTO;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.servicios.AsistenciaService;
import com.example.proyectogym.servicios.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;
    @Autowired
    private SocioService socioService;

    @GetMapping("/listar")
    public List<Asistencia> getAllAsistencias() {
        List<Asistencia> asistencias = asistenciaService.getAll();
        return asistencias;
    }

    @GetMapping()
    public Asistencia getById(@RequestParam Integer id) {
        Asistencia asistencia = asistenciaService.findById(id);
        return asistencia;
    }
    @PostMapping("/entrada")
    public String registrarEntrada(@RequestBody AsistenciaDto dto) {
        return asistenciaService.entradaAsistencia(dto);
    }
    @PutMapping("/salida")
    public String salidaAsistencia(@RequestBody AsistenciaDto asistenciaDto) {
        return asistenciaService.salidaAsistencia(asistenciaDto);
    }
}
