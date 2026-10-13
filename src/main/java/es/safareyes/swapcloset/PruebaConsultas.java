package es.safareyes.swapcloset;

import es.safareyes.swapcloset.modelo.Prenda;
import es.safareyes.swapcloset.repository.CategoriaRepository;
import es.safareyes.swapcloset.repository.PrendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PruebaConsultas implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final PrendaRepository prendaRepository;

    @Override
    public void run(String... args) {
        log.info("Categorías en la BD: {}", categoriaRepository.count());

        for (Prenda p : prendaRepository.findByDisponibilidadTrue()) {
            log.info("Disponible: {}", p.getTitulo());
        }

        for (Prenda p : prendaRepository.findByMarca("Nike")) {
            log.info("Marca Nike: {}", p.getTitulo());
        }

        for (Prenda p : prendaRepository.findByTituloContainingIgnoreCase("camiseta")) {
            log.info("Contiene 'camiseta': {}", p.getTitulo());
        }
    }
}