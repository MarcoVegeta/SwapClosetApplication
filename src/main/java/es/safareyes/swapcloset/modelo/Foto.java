package es.safareyes.swapcloset.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foto")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto")
    private Integer id_foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;

    @Column(name = "url")
    private String url;

    @Column(name = "orden")
    private Short orden = 1;
}
