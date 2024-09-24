package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "abono",schema = "Gym", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Abono {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "nombre")
        private String nombre;

        @Column(name = "descripcion")
        private String descripcion;

        @Column(name ="duracion")
        private Integer duracion;

        @Column(name = "precio")
        private Double precio;
}