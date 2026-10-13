package es.safareyes.swapcloset.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mensajes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Integer id_mensaje;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @Column(name = "hora_envio")
    private LocalTime horaEnvio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "mensajes_envia",
            joinColumns = @JoinColumn(name = "id_mensaje"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private Set<Perfil> emisores = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "mensajes_recibe",
            joinColumns = @JoinColumn(name = "id_mensaje"),
            inverseJoinColumns = @JoinColumn(name = "id_perfil")
    )
    private Set<Perfil> receptores = new HashSet<>();

    @PrePersist
    void alCrear() {
        if (fechaEnvio == null) {
            fechaEnvio = LocalDate.now();
        }
        if (horaEnvio == null) {
            horaEnvio = LocalTime.now();
        }
    }
}
