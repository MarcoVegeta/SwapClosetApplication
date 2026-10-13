package es.safareyes.swapcloset.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "perfil")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer id_perfil;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "biografia", columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "talla_zapatos")
    private String tallaZapatos;

    @Column(name = "talla_camiseta")
    private String tallaCamiseta;

    @Column(name = "talla_pantalon")
    private String tallaPantalon;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "perfil_estilos",
            joinColumns = @JoinColumn(name = "id_perfil"),
            inverseJoinColumns = @JoinColumn(name = "id_estilo")
    )
    private Set<Estilo> estilos = new HashSet<>();
}
