package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Mensaje;
import es.safareyes.swapcloset.modelo.Perfil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MensajeRepositoryTest {

    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Test
    void guardaUnMensajeConFechaYHora() {
        Mensaje m = new Mensaje();
        m.setContenido("Hola, ¿sigue disponible?");

        Mensaje guardado = mensajeRepository.saveAndFlush(m);

        assertThat(guardado.getId_mensaje()).isNotNull();
        assertThat(guardado.getFechaEnvio()).isNotNull();
        assertThat(guardado.getHoraEnvio()).isNotNull();
    }

    @Test
    void guardaUnMensajeConEmisorYReceptor() {
        Perfil perfil = perfilRepository.findAll().get(0);

        Mensaje m = new Mensaje();
        m.setContenido("Mensaje con emisor y receptor");
        m.getEmisores().add(perfil);
        m.getReceptores().add(perfil);

        Mensaje guardado = mensajeRepository.saveAndFlush(m);

        assertThat(guardado.getEmisores()).hasSize(1);
        assertThat(guardado.getReceptores()).hasSize(1);
    }
}