package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Estilo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EstiloRepositoryTest {

    @Autowired
    private EstiloRepository estiloRepository;

    @Test
    void guardaYBuscaUnEstilo() {
        Estilo e = new Estilo();
        e.setNombre("Elegante" + System.nanoTime());

        Estilo guardado = estiloRepository.saveAndFlush(e);

        assertThat(guardado.getId_estilo()).isNotNull();
        assertThat(estiloRepository.findById(guardado.getId_estilo())).isPresent();
    }
}