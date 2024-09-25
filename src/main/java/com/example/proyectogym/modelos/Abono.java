package com.example.proyectogym.modelos;

import com.example.proyectogym.enumerados.TipoAbono;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "abono",schema = "gym", catalog = "postgres")
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
        @Enumerated(EnumType.ORDINAL)
        private TipoAbono duracion;

        @Column(name = "precio")
        private Double precio;
}
