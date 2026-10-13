package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void guardaYBuscaUnaCategoria() {
        Categoria c = new Categoria();
        c.setNombre("Bufandas" + System.nanoTime());

        Categoria guardada = categoriaRepository.saveAndFlush(c);

        assertThat(guardada.getId_categoria()).isNotNull();
        assertThat(categoriaRepository.findById(guardada.getId_categoria())).isPresent();
    }

    @Test
    void cuentaLasCategorias() {
        long antes = categoriaRepository.count();

        Categoria c = new Categoria();
        c.setNombre("Guantes" + System.nanoTime());
        categoriaRepository.saveAndFlush(c);

        assertThat(categoriaRepository.count()).isEqualTo(antes + 1);
    }
}