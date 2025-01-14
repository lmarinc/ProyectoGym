package com.example.proyectogym.servicios;

import com.example.proyectogym.dto.MensajeAbonoSocioDTO;
import com.example.proyectogym.dto.RenovarAbonoVigenteDTO;
import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import com.example.proyectogym.repositorios.AbonoSocioRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AbonoServiceIntegracionTest {

    @InjectMocks
    private AbonoService abonoService;

    @Mock
    private AbonoRepositorio abonoRepositorio;

    @Mock
    private AbonoSocioService abonoSocioService;

    @Mock
    private SocioRepositorio socioRepositorio;

    @Mock
    private AbonoSocioRepositorio abonoSocioRepositorio;

    @Mock
    private SocioService socioService;

    @Test
    public void testFindAllIntegracion(){

        //Given
         List<Abono> abonoPorDefecto = new ArrayList<>();
        Abono abono1 = new Abono();
        abono1.setNombre("Abono Mensual");
        abono1.setPrecio(50.0);
        abono1.setTipoAbono(TipoAbono.MENSUAL);
        abonoRepositorio.save(abono1);

        Abono abono2 = new Abono();
        abono2.setNombre("Abono Trimestral");
        abono2.setPrecio(120.0);
        abono2.setTipoAbono(TipoAbono.TRIMESTRAL);
        abonoRepositorio.save(abono2);

        abonoPorDefecto.add(abono1);
        abonoPorDefecto.add(abono2);

        Mockito.when(abonoRepositorio.findAll()).thenReturn(abonoPorDefecto);

        //When
        List<Abono> abonos = abonoService.getAll();

        //Then
        assertEquals(2,abonos.size());
        Mockito.verify(abonoRepositorio,Mockito.times(1)).findAll();
    }

    @Test
    public void testBuscarPorIdIntegracion(){
        //Given
        Mockito.when(abonoRepositorio.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));

        //Then
        assertThrows(Exception.class,()->abonoService.getAbonoPorId(3));
        Mockito.verify(abonoRepositorio,Mockito.times(1)).findById(3);
    }

    @Test
    public void testRenovarAbonoPositivo() {
        // Given
        RenovarAbonoVigenteDTO dto = new RenovarAbonoVigenteDTO();
        dto.setIdSocio(1);

        Socio socio = new Socio();
        socio.setId(1);
        socio.setNombre("Juan");
        socio.setApellidos("Pérez");

        Abono abono = new Abono();
        abono.setDuracion(30); // Set a valid duration
        abono.setPrecio(50.0);

        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);
        abonoSocio.setAbono(abono);
        abonoSocio.setFechaInicio(LocalDate.now());
        abonoSocio.setFechaFin(LocalDate.now().plusMonths(1));
        abonoSocio.setPrecio(50.0);

        MensajeAbonoSocioDTO mensajeAbonoSocioDTO = new MensajeAbonoSocioDTO();
        mensajeAbonoSocioDTO.setMensaje("Abono renovado exitosamente para Juan Pérez");
        mensajeAbonoSocioDTO.setNuevoAbonoSocio(abonoSocio);

        when(socioRepositorio.findById(1)).thenReturn(Optional.of(socio));
        when(abonoSocioRepositorio.findFirstBySocioIdOrderByFechaFinDesc(1)).thenReturn(abonoSocio);
        when(abonoSocioService.guardar(Mockito.any(AbonoSocio.class))).thenReturn(abonoSocio);

        // When
        MensajeAbonoSocioDTO result = abonoService.renovarAbonoVigente(dto);

        // Then
        assertNotNull(result);
        assertEquals("Abono renovado exitosamente para Juan Pérez", result.getMensaje());
        Mockito.verify(socioRepositorio, Mockito.times(1)).findById(1);
        Mockito.verify(abonoSocioRepositorio, Mockito.times(1)).findFirstBySocioIdOrderByFechaFinDesc(1);
        Mockito.verify(abonoSocioService, Mockito.times(1)).guardar(Mockito.any(AbonoSocio.class));
    }
}
