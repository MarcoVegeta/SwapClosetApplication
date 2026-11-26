package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Prenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrendaRepository extends JpaRepository<Prenda, Integer> {

    // Derivadas
    List<Prenda> findByDisponibilidadTrue();

    List<Prenda> findByMarca(String marca);

    List<Prenda> findByTituloContainingIgnoreCase(String texto);

    // Derivada paginada
    Page<Prenda> findByTituloContainingIgnoreCase(String texto, Pageable pageable);

    // JPQL con JOIN
    @Query("""
            SELECT p FROM Prenda p
            JOIN p.categoria c
            WHERE c.nombre = :nombre
            """)
    List<Prenda> buscarPorCategoria(@Param("nombre") String nombre);

    // SQL nativo
    @Query(value = """
            SELECT c.nombre, COUNT(p.id_prenda) AS total
            FROM categoria c
            LEFT JOIN prenda p ON p.id_categoria = c.id_categoria
            GROUP BY c.nombre
            ORDER BY total DESC
            """, nativeQuery = true)
    List<Object[]> contarPrendasPorCategoria();

    // Para las reglas del servicio
    @Query("""
            SELECT COUNT(p) FROM Prenda p
            WHERE p.perfil.id_perfil = :idPerfil
              AND LOWER(p.titulo) = LOWER(:titulo)
            """)
    long contarTituloEnPerfil(@Param("idPerfil") Integer idPerfil,
                              @Param("titulo") String titulo);

    @Query("""
            SELECT COUNT(p) FROM Prenda p
            WHERE p.perfil.id_perfil = :idPerfil
              AND LOWER(p.titulo) = LOWER(:titulo)
              AND p.id_prenda <> :idPrenda
            """)
    long contarTituloEnPerfilExcepto(@Param("idPerfil") Integer idPerfil,
                                     @Param("titulo") String titulo,
                                     @Param("idPrenda") Integer idPrenda);

    @Query("SELECT COUNT(p) FROM Prenda p WHERE p.categoria.id_categoria = :idCategoria")
    long contarPorCategoria(@Param("idCategoria") Integer idCategoria);
}