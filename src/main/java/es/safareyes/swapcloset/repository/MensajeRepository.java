package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}