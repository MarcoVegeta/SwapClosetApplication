package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Foto;
import es.safareyes.swapcloset.modelo.Prenda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FotoRepositoryTest {

    @Autowired
    private FotoRepository fotoRepository;
    @Autowired
    private PrendaRepository prendaRepository;

    @Test
    void guardaUnaFotoConOrdenPorDefecto() {
        Prenda prenda = prendaRepository.findAll().get(0);

        Foto f = new Foto();
        f.setPrenda(prenda);
        f.setUrl("http://ejemplo.com/foto.jpg");

        Foto guardada = fotoRepository.saveAndFlush(f);

        assertThat(guardada.getId_foto()).isNotNull();
        assertThat(guardada.getOrden()).isEqualTo((short) 1);
    }
}