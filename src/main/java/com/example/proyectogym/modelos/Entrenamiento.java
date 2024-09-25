package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "entrenamiento",schema = "gym", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"monitor"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"monitor"})

public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false   )
    private Integer id;

    @Column(name = "nombre_entrenamiento", nullable = false)
    private String nombreEntrenamiento;

    @Column(name = "frecuencia" , nullable = false)
    private Integer frecuencia;

    @Column(name = "duracion" , nullable = false)
    private Integer duracion;

    @Column (name = "nivel_dificultad", nullable = false)
    private String nivelDificultad;

    @Column (name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column (name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_monitor", nullable = false)
    private Monitor monitor;
}
