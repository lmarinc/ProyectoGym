package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "asistencia",schema = "gym", catalog = "postgres")
@Getter
@Setter
@ToString (exclude = {"socio"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (exclude = {"socio"})

public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false   )
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_entrada", nullable = false)
    private Time horaEntrada;

    @Column(name = "hora_salida")
    private Time horaSalida;

    @ManyToOne
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;
}
