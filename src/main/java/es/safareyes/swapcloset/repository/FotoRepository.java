package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
}
