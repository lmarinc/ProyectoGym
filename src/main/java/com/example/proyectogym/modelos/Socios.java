package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "socios",schema = "Gym", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Socios {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false   )
        private Integer id;

        @Column(name = "nombre", nullable = false)
        private String nombre;

        @Column(name = "apellidos", nullable = false)
        private String apellidos;

        @Column(name = "dni" , nullable = false)
        private String dni;

        @Column(name = "telefono" )
        private String telefono;

        @Column(name = "correo" )
        private String correo;

        @Column(name = "es_activo" , columnDefinition = "boolean default true")
        private Boolean esActivo;


}
