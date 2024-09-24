package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "abonosocio",schema = "Gym", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode


public class AbonoSocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false   )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false)
    private Socios socio;

    @ManyToOne
    @JoinColumn(name = "id_abono", nullable = false)
    private Abono abono;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "precio", nullable = false)
    private Double precio;
}
