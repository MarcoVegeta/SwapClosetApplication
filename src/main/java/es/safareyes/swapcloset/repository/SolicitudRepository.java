package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
}
