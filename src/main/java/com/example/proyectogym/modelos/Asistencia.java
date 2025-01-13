package com.example.proyectogym.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
//@Table(name = "asistencia",schema = "gym", catalog = "postgres")
@Table(name = "asistencia",schema = "gym")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    @JsonIgnore
    private Socio socio;
}
