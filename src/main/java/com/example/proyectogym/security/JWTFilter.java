package com.example.proyectogym.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String cabeceraAutentificacion = request.getHeader("Authorization");
        final String jwt;

        if(cabeceraAutentificacion == null || !cabeceraAutentificacion.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = cabeceraAutentificacion.substring(7);
    }


}
