package com.example.proyectogym.servicios;

import com.example.proyectogym.repositorios.SocioRepositorio;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
public class SocioServiceIntegracionTest {

    @InjectMocks
    private SocioService socioService;

    @Mock
    private SocioRepositorio socioRepositorio;


}
