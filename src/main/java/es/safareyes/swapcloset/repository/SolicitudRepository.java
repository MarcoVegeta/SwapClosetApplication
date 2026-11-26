package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    @Query("""
            SELECT COUNT(DISTINCT s) FROM Solicitud s
            JOIN s.fotos f
            WHERE f.prenda.id_prenda = :idPrenda
            """)
    long contarPorPrenda(@Param("idPrenda") Integer idPrenda);
}