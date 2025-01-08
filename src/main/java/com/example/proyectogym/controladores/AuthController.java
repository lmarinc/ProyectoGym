package com.example.proyectogym.controladores;

import com.example.proyectogym.dto.AuthRequestDTO;
import com.example.proyectogym.dto.AuthResponseDTO;
import com.example.proyectogym.dto.MensajeDTO;
import com.example.proyectogym.enumerados.Rol;
import com.example.proyectogym.modelos.Usuario;
import com.example.proyectogym.security.JWTService;
import com.example.proyectogym.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UsuarioService usuarioService, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDTO authRequest) {
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(authRequest.getUsername());
            usuario.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            usuario.setRol(Rol.USUARIO); // Asignar rol USUARIO por defecto
            usuarioService.registerUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO("Usuario registrado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeDTO("Error en el registro: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            Usuario usuario = usuarioService.authenticate(authRequest.getUsername(), authRequest.getPassword());
            String token = jwtService.generateToken(usuario);
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MensajeDTO("Error en autenticación: " + e.getMessage()));
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajeDTO("Error: " + e.getMessage()));
    }
}
