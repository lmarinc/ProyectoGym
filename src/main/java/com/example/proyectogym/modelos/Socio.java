package com.example.proyectogym.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socio",schema = "gym", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Socio {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Column(name = "nombre", nullable = false)
        private String nombre;

        @Column(name = "apellidos", nullable = false)
        private String apellidos;

        @Column(name = "dni", nullable = false)
        private String dni;

        @Column(name = "telefono")
        private String telefono;

        @Column(name = "correo")
        private String correo;

        @Column(name = "es_activo", columnDefinition = "boolean default true")
        private Boolean esActivo = true;

        @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnore
        private List<AbonoSocio> abonoSocios = new ArrayList<>();

        @OneToMany(mappedBy = "socio",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        @JsonIgnore
        private List<Asistencia> asistencias;

        @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnore
        private List<SocioEntrenamiento> socioEntrenamientos = new ArrayList<>();
}
