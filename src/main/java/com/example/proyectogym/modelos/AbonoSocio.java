package com.example.proyectogym.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
//@Table(name = "abonosocio",schema = "gym", catalog = "postgres")
@Table(name = "abonosocio",schema = "gym")
@Getter
@Setter
@ToString (exclude = {"socio", "abono"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"socio", "abono"})


public class AbonoSocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false   )
    private Integer id;


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    @JsonIgnore
    private Socio socio;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_abono", nullable = false)
    @JsonIgnore
    private Abono abono;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "precio", nullable = false)
    private Double precio;
}
