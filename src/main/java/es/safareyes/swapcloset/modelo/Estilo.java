package es.safareyes.swapcloset.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estilos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estilo")
    private Integer id_estilo;

    @Column(name = "nombre")
    private String nombre;
}