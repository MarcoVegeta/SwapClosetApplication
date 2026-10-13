package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Notificacion;
import es.safareyes.swapcloset.modelo.Perfil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Test
    void guardaUnaNotificacionNoLeida() {
        Perfil perfil = perfilRepository.findAll().get(0);

        Notificacion n = new Notificacion();
        n.setPerfil(perfil);
        n.setTipo("info");
        n.setMensaje("Tienes una solicitud nueva");

        Notificacion guardada = notificacionRepository.saveAndFlush(n);

        assertThat(guardada.getId_notificacion()).isNotNull();
        assertThat(guardada.getLeida()).isFalse();
        assertThat(guardada.getFecha()).isNotNull();
    }
}