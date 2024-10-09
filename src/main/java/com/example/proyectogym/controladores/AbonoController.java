package com.example.proyectogym.controladores;

import com.example.proyectogym.dto.RenovarAbonoSocioDTO;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.servicios.AbonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abono")
public class AbonoController {
    @Autowired
    private AbonoService abonoService;

    @GetMapping("/listar")
    public List<Abono> getAllAbonos() {
        List<Abono> abonos = abonoService.getAll();

        return abonos;
    }
    @GetMapping()
    public Abono getById(@RequestParam Integer id) {
        Abono abono = abonoService.getAbonoPorId(id);
        return abono;
    }
    @GetMapping("/{id}")
    public Abono getbyIdPath(@PathVariable Integer id) {
        Abono abono = abonoService.getAbonoPorId(id);
        return abono;
    }
    @PostMapping()
    public Abono guardar(@RequestBody Abono abono) {
        Abono abonoGuardado = abonoService.guardar(abono);
        return abonoGuardado;
    }

    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return abonoService.eliminarPorId(id);
    }

    @PostMapping("/renovar")
    public AbonoSocio renovarAbono(@RequestBody RenovarAbonoSocioDTO dto) {
        return abonoService.renovarAbono(dto);
    }

}
