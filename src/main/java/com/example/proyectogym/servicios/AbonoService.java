package com.example.proyectogym.servicios;


import com.example.proyectogym.dto.MensajeAbonoSocioDTO;
import com.example.proyectogym.dto.RenovarAbonoSocioDTO;
import com.example.proyectogym.dto.RenovarAbonoVigenteDTO;
import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import com.example.proyectogym.repositorios.AbonoSocioRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AbonoService {


    private final AbonoSocioService abonoSocioService;
    private AbonoRepositorio abonoRepositorio;
    private AbonoSocioRepositorio abonoSocioRepositorio;
    private SocioRepositorio socioRepositorio;

    /**
     * Método que devuelve una lista de abonos por tipo
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

        if(abono == null){
            throw new IllegalArgumentException("Abono no encontrado con ID: " + id);
        }
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
    public List<Abono> getAll() {
        try {
            List<Abono> abonos = abonoRepositorio.findAll();
            if (abonos.isEmpty()) {
                throw new Exception("La lista de abonos está vacía.");
            }
            return abonos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
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

    /**
     * Método para renovar un abono
     * @param dto
     * @return
     */


    /**
     * Método para renovar un abono
     * @param dto
     * @return
     */
    public AbonoSocio renovarAbono(RenovarAbonoSocioDTO dto) {
        Socio socio = socioRepositorio.findById(dto.getIdSocio())
                .orElseThrow(() -> new IllegalArgumentException("Socio no encontrado con ID: " + dto.getIdSocio()));


        Abono abono = abonoRepositorio.findById(dto.getIdAbono())
                .orElseThrow(() -> new IllegalArgumentException("Abono no encontrado con ID: " + dto.getIdAbono()));


        LocalDate fechaInicio;
        List<AbonoSocio> abonosPrevios = socio.getAbonoSocios();
        if (abonosPrevios == null || abonosPrevios.isEmpty()) {
            throw new IllegalArgumentException("El socio no tiene abonos previos");
        } else {
            LocalDate fechaFinUltimo = abonosPrevios.get(abonosPrevios.size() - 1).getFechaFin();
            fechaInicio = fechaFinUltimo;
        }

        LocalDate fechaFin = fechaInicio.plusDays(abono.getDuracion());

        AbonoSocio nuevoAbonoSocio = new AbonoSocio();
        nuevoAbonoSocio.setSocio(socio);
        nuevoAbonoSocio.setAbono(abono);
        nuevoAbonoSocio.setFechaInicio(fechaInicio);
        nuevoAbonoSocio.setFechaFin(fechaFin);
        nuevoAbonoSocio.setPrecio(abono.getPrecio());  //

        return abonoSocioService.guardar(nuevoAbonoSocio);
    }

    private AbonoSocio crearNuevoAbonoSocio(Socio socio, AbonoSocio abonoVigente) {

        LocalDate fechaInicio;
        if (abonoVigente.getFechaFin().isBefore(LocalDate.now())) {
            fechaInicio = LocalDate.now();  // Si está caducado, nuevo inicio es hoy
        } else {
            fechaInicio = abonoVigente.getFechaFin();  // Si no, sigue desde la fecha de fin actual
        }

        Abono abono = abonoVigente.getAbono();
        LocalDate nuevaFechaFin = fechaInicio.plusDays(abono.getDuracion());

        AbonoSocio nuevoAbonoSocio = new AbonoSocio();
        nuevoAbonoSocio.setSocio(socio);
        nuevoAbonoSocio.setAbono(abono);
        nuevoAbonoSocio.setFechaInicio(fechaInicio);
        nuevoAbonoSocio.setFechaFin(nuevaFechaFin);
        nuevoAbonoSocio.setPrecio(abono.getPrecio());

        return nuevoAbonoSocio;
    }

    /**
     * Método para renovar un abono vigente
     * @param dto
     * @return
     */
    public MensajeAbonoSocioDTO renovarAbonoVigente(RenovarAbonoVigenteDTO dto) {
        try {
            Socio socio = socioRepositorio.findById(dto.getIdSocio())
                    .orElseThrow(() -> new Exception("Socio no encontrado con ID: " + dto.getIdSocio()));

            AbonoSocio abonoVigente = abonoSocioRepositorio.findFirstBySocioIdOrderByFechaFinDesc(dto.getIdSocio());

            if (abonoVigente == null) {
                return new MensajeAbonoSocioDTO("El socio no tiene abonos previos.", null);
            }

            AbonoSocio nuevoAbonoSocio = crearNuevoAbonoSocio(socio, abonoVigente);

            nuevoAbonoSocio = abonoSocioService.guardar(nuevoAbonoSocio);

            return new MensajeAbonoSocioDTO("Abono renovado exitosamente para "+ socio.getNombre() + " " + socio.getApellidos(), nuevoAbonoSocio);
        } catch (Exception e) {
            return new MensajeAbonoSocioDTO("Error al renovar el abono: " + e.getMessage(), null);
        }


    }




}






