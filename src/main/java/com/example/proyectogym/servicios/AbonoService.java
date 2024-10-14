package com.example.proyectogym.servicios;


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

    public AbonoSocio renovarAbono(RenovarAbonoSocioDTO dto) {
        // Obtener el socio por ID
        Socio socio = socioRepositorio.findById(dto.getIdSocio())
                .orElseThrow(() -> new IllegalArgumentException("Socio no encontrado con ID: " + dto.getIdSocio()));

        // Obtener el abono por ID
        Abono abono = abonoRepositorio.findById(dto.getIdAbono())
                .orElseThrow(() -> new IllegalArgumentException("Abono no encontrado con ID: " + dto.getIdAbono()));

        // Determinar la fecha de inicio
        LocalDate fechaInicio;
        List<AbonoSocio> abonosPrevios = socio.getAbonoSocios();

        if (abonosPrevios == null || abonosPrevios.isEmpty()) {
            // Si no hay registros previos, usar la fecha de hoy
            throw new IllegalArgumentException("El socio no tiene abonos previos");
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
        nuevoAbonoSocio.setPrecio(abono.getPrecio());  //

        // Guardar el nuevo AbonoSocio
        return abonoSocioService.guardar(nuevoAbonoSocio);
    }

    /**
     * Método para renovar un abono vigente
     * @param dto
     * @return
     */
    public AbonoSocio renovarAbonoVigente(RenovarAbonoVigenteDTO dto) {
        try {
            // Obtener el socio por ID
            Socio socio = socioRepositorio.findById(dto.getIdSocio())
                    .orElseThrow(() -> new Exception("Socio no encontrado con ID: " + dto.getIdSocio()));

            // Obtener los abonos previos del socio
            List<AbonoSocio> abonosPrevios = socio.getAbonoSocios();

            if (abonosPrevios == null || abonosPrevios.isEmpty()) {
                // Si el socio no tiene abonos previos, mostrar mensaje
                System.out.println("El socio no tiene abonos previos.");
                return null;
            }

            // Obtener el último abono del socio
            AbonoSocio abonoVigente = abonosPrevios.getLast();

            // Determinar si el abono ha caducado
            LocalDate fechaInicio;
            if (abonoVigente.getFechaFin().isBefore(LocalDate.now())) {
                // El abono ha caducado, renovarlo con la fecha de hoy
                System.out.println("El abono ha caducado. Se renueva con fecha de hoy.");
                fechaInicio = LocalDate.now();
            } else {
                // Si el abono aún es vigente, renovarlo desde la fecha de finalización del abono vigente
                fechaInicio = abonoVigente.getFechaFin();
            }

            // Obtener el abono asociado
            Abono abono = abonoVigente.getAbono(); // Se renueva con el mismo abono del último

            // Calcular la nueva fecha de fin
            LocalDate nuevaFechaFin = fechaInicio.plusDays(abono.getDuracion());

            // Crear el nuevo registro de AbonoSocio
            AbonoSocio nuevoAbonoSocio = new AbonoSocio();
            nuevoAbonoSocio.setSocio(socio);
            nuevoAbonoSocio.setAbono(abono);
            nuevoAbonoSocio.setFechaInicio(fechaInicio);
            nuevoAbonoSocio.setFechaFin(nuevaFechaFin);
            nuevoAbonoSocio.setPrecio(abono.getPrecio());

            // Guardar el nuevo AbonoSocio
            return abonoSocioService.guardar(nuevoAbonoSocio);

        } catch (Exception e) {
            // Manejar los errores y mostrarlos en consola
            System.out.println("Error al renovar el abono: " + e.getMessage());
            return null;
        }
    }



}
