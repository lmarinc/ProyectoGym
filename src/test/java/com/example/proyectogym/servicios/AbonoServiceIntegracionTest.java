package com.example.proyectogym.servicios;

import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AbonoServiceIntegracionTest {

    @InjectMocks
    private AbonoService abonoService;

    @Mock
    private AbonoRepositorio abonoRepositorio;

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

//    @Test
//    public void testBuscarPorIdIntegracion(){
//        //Given
//        Mockito.when(abonoRepositorio.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));
//
//        //Then
//        assertThrows(Exception.class,()->abonoService.getAbonoPorId(3));
//        Mockito.verify(abonoRepositorio,Mockito.times(1)).findById(3);
//    }
}
