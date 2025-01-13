package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "socio_entrenamiento", schema = "gym", catalog = "postgres")
@Table(name = "socio_entrenamiento", schema = "gym")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SocioEntrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio socio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrenamiento", nullable = false)
    private Entrenamiento entrenamiento;


}
