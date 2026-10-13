package es.safareyes.swapcloset.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id_usuario;

    @Column(name = "username")
    private String username;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "rol")
    private String rol;
}