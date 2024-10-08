package com.example.proyectogym.servicios;

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



    public String eliminarPorId(Integer id) {
        Socio socio = socioRepositorio.findById(id).orElse(null);

        if (socio == null) {
            return "Socio no encontrado";
        }

        try {
            socioRepositorio.delete(socio);
            return "Socio eliminado";
        } catch (Exception e) {
            return "Error al eliminar el socio";
        }
    }

    /**
     * Método para guardar un socio
     * @param socio
     * @return
     */

    public Socio guardar(Socio socio) {
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
        return socioRepositorio.save(socio);
    }



}
