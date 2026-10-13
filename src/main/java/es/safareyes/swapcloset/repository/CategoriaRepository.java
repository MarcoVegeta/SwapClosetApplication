package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}