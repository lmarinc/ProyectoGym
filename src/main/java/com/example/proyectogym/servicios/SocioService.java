package com.example.proyectogym.servicios;

import com.example.proyectogym.dto.MensajeDTO;
import com.example.proyectogym.dto.SocioAbonoDTO;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import com.example.proyectogym.repositorios.AbonoSocioRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SocioService {

    private SocioRepositorio socioRepositorio;
    private AbonoRepositorio abonoRepository;
    private AbonoSocioRepositorio abonoSocioRepositorio;

    /**
     * Método para eliminar un socio
     * @param id
     */

    public void eliminar(Integer id) {
        socioRepositorio.deleteById(id);
    }


    /**
     * Método para eliminar un socio por su id
     * @param id
     * @return
     */
    public MensajeDTO eliminarPorId(Integer id) {
        Socio socio = socioRepositorio.findById(id).orElse(null);
        if (socio == null) {
            return new MensajeDTO("Socio no encontrado");
        }
        if (abonoSocioRepositorio.existsBySocioIdAndFechaFinAfter(id, LocalDate.now())) {
            return new MensajeDTO("El socio no puede ser eliminado porque tiene un abono vigente.");
        }

        try {
            socioRepositorio.delete(socio);
            return new MensajeDTO("Socio eliminado");
        } catch (Exception e) {
            return new MensajeDTO("Error al eliminar el socio: " + e.getMessage());
        }
    }

    /**
     * Método para guardar un socio
     * @param socio
     * @return
     */

    public Socio guardar(Socio socio) {
        if (socio.getNombre() == null || socio.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
        if (socio.getApellidos() == null || socio.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos no pueden estar en blanco.");
        }
        if (socio.getDni() == null || socio.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar en blanco.");
        }
        if (socio.getTelefono() == null || socio.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar en blanco.");
        }
        if (socio.getCorreo() == null || socio.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar en blanco.");
        }
        return socioRepositorio.save(socio);
    }

    /**
     * Método que devuelve una lista de todos los socios
     * @return
     */

    public List<Socio> getAll() {
        return socioRepositorio.findAll();
    }

    /**
     * Método para buscar un socio por su id
     * @param id
     * @return
     */

    public Socio getSocioPorId(Integer id){
        Socio socio = socioRepositorio.findById(id).orElse(null);
        return socio;
    }

    /**
     * Método para guardar un socio con un abono
     * @param dto
     * @return
     */
    public Socio guardarSocioConAbono(SocioAbonoDTO dto) {

        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellidos(dto.getApellidos());
        socio.setDni(dto.getDni());
        socio.setTelefono(dto.getTelefono());
        socio.setCorreo(dto.getCorreo());
        socio.setEsActivo(true);


        Abono abono = abonoRepository.findById(dto.getAbonoId())
                .orElseThrow(() -> new IllegalArgumentException("Abono no encontrado con id: " + dto.getAbonoId()));


        LocalDate fechaInicioParsed = LocalDate.parse(dto.getFechaInicio());


        LocalDate fechaFin = fechaInicioParsed.plusDays(abono.getDuracion());


        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);
        abonoSocio.setAbono(abono);
        abonoSocio.setFechaInicio(fechaInicioParsed);
        abonoSocio.setFechaFin(fechaFin);
        abonoSocio.setPrecio(dto.getPrecio());


        socio.getAbonoSocios().add(abonoSocio);


        return socioRepositorio.save(socio);
    }


    public MensajeDTO totalGasto(Integer socioId) {
        Socio socio = socioRepositorio.findById(socioId).orElse(null);

        if (socio == null) {
            return new MensajeDTO("Socio no encontrado con ID: " + socioId);
        }
        Double totalGasto = abonoSocioRepositorio.obtenerTotalGastoPorSocio(socioId);

        if (totalGasto == null || totalGasto == 0) {
            return new MensajeDTO("El socio no tiene abonos registrados.");
        }

        return new MensajeDTO("El socio con ID " + socioId + " ha gastado un total de " + totalGasto + "€ en abonos.");
    }
}
