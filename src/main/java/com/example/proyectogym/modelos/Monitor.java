package com.example.proyectogym.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "monitor",schema = "gym", catalog = "postgres")
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

//    @OneToMany(mappedBy = "monitor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<Entrenamiento> entrenamientos;

}
