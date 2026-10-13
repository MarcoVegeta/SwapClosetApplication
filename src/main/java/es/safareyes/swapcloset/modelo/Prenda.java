package es.safareyes.swapcloset.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prenda")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenda")
    private Integer id_prenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "estado")
    private String estado = "disponible";

    @Column(name = "marca")
    private String marca;

    @Column(name = "talla")
    private String talla;

    @Column(name = "disponibilidad")
    private Boolean disponibilidad = true;

    @Column(name = "permite_prestamo")
    private Boolean permitePrestamo = false;

    @Column(name = "permite_intercambio")
    private Boolean permiteIntercambio = false;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @PrePersist
    void alCrear() {
        if (fechaPublicacion == null) {
            fechaPublicacion = LocalDateTime.now();
        }
    }
}