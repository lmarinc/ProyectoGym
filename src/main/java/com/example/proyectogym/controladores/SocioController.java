package com.example.proyectogym.controladores;


import com.example.proyectogym.dto.RenovarAbonoSocioDTO;
import com.example.proyectogym.dto.SocioAbonoDTO;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import com.example.proyectogym.servicios.AbonoService;
import com.example.proyectogym.servicios.AbonoSocioService;
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

    @PostMapping("/guardar")
    public Socio guardarSocioConAbono(@RequestBody SocioAbonoDTO dto) {

        // Crear un nuevo Socio y asignar los valores del DTO
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellidos(dto.getApellidos());
        socio.setDni(dto.getDni());
        socio.setTelefono(dto.getTelefono());
        socio.setCorreo(dto.getCorreo());
        socio.setEsActivo(true);  // Establecer esActivo a true por defecto

        // Buscar el Abono por su ID
        Abono abono = abonoRepository.findById(dto.getAbonoId())
                .orElseThrow(() -> new IllegalArgumentException("Abono no encontrado con id: " + dto.getAbonoId()));

        // Parsear la fecha de inicio
        LocalDate fechaInicioParsed = LocalDate.parse(dto.getFechaInicio());

        // Calcular la fecha de fin en base a la duración del abono
        LocalDate fechaFin = fechaInicioParsed.plusDays(abono.getDuracion());

        // Crear el registro en AbonoSocio
        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);  // Asociar el socio al abono
        abonoSocio.setAbono(abono);
        abonoSocio.setFechaInicio(fechaInicioParsed);
        abonoSocio.setFechaFin(fechaFin);
        abonoSocio.setPrecio(dto.getPrecio());

        // Añadir el AbonoSocio al socio sin exponerlo en el JSON de entrada
        socio.getAbonoSocios().add(abonoSocio);

        // Guardar el socio (en cascada se guarda el AbonoSocio)
        return socioService.guardar(socio);
    }

    @PostMapping("/renovar-abono")
    public AbonoSocio renovarAbono(@RequestBody RenovarAbonoSocioDTO dto) {
        // Obtener el socio por ID
        Socio socio = socioService.getSocioPorId(dto.getIdSocio());
        if (socio == null) {
            throw new IllegalArgumentException("Socio no encontrado con ID: " + dto.getIdSocio());
        }

        // Obtener el abono por ID
        Abono abono = abonoService.getAbonoPorId(dto.getIdAbono());
        if (abono == null) {
            throw new IllegalArgumentException("Abono no encontrado con ID: " + dto.getIdAbono());
        }

        // Determinar la fecha de inicio
        LocalDate fechaInicio;
        List<AbonoSocio> abonosPrevios = socio.getAbonoSocios();

        if (abonosPrevios == null || abonosPrevios.isEmpty()) {
            // Si no hay registros previos, usar la fecha de hoy
            fechaInicio = LocalDate.now();
        } else {
            // Si hay registros previos, tomar la fecha de fin del último registro
            LocalDate fechaFinUltimo = abonosPrevios.get(abonosPrevios.size() - 1).getFechaFin();
            fechaInicio = fechaFinUltimo;
        }

        // Calcular la fecha de fin basada en la duración del abono
        LocalDate fechaFin = fechaInicio.plusDays(abono.getDuracion());

        // Crear el nuevo registro de AbonoSocio
        AbonoSocio nuevoAbonoSocio = new AbonoSocio();
        nuevoAbonoSocio.setSocio(socio);
        nuevoAbonoSocio.setAbono(abono);
        nuevoAbonoSocio.setFechaInicio(fechaInicio);
        nuevoAbonoSocio.setFechaFin(fechaFin);
        nuevoAbonoSocio.setPrecio(abono.getPrecio()); // Si es necesario, puedes establecer el precio del abono

        // Guardar el nuevo AbonoSocio
        return abonoSocioService.guardar(nuevoAbonoSocio);
    }

}


