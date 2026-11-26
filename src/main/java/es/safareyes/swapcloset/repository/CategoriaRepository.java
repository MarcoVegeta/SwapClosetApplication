package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    boolean existsByNombreIgnoreCase(String nombre);

    @Query("""
            SELECT COUNT(c) FROM Categoria c
            WHERE LOWER(c.nombre) = LOWER(:nombre) AND c.id_categoria <> :id
            """)
    long contarNombreExcepto(@Param("nombre") String nombre, @Param("id") Integer id);


}