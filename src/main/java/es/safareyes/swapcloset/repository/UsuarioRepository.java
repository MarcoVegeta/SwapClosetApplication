package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}