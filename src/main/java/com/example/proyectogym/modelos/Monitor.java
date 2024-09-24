package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "monitor",schema = "Gym", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode



public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false   )
    private Integer id;


    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "dni" , nullable = false)
    private String dni;

    @Column(name = "salario" , nullable = false)
    private Double salario;

//    @OneToMany(targetEntity = Entrenamientos.class, mappedBy = "monitor", fetch = FetchType.LAZY)
//    private Set<Entrenamientos> entrenamientos;
}
