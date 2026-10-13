package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Perfil;
import es.safareyes.swapcloset.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SolicitudRepositoryTest {

    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Test
    void guardaUnaSolicitudPendiente() {
        Perfil perfil = perfilRepository.findAll().get(0);

        Solicitud s = new Solicitud();
        s.setPerfil(perfil);
        s.setTipoSolicitud("prestamo");

        Solicitud guardada = solicitudRepository.saveAndFlush(s);

        assertThat(guardada.getId_solicitud()).isNotNull();
        assertThat(guardada.getEstado()).isEqualTo("pendiente");
        assertThat(guardada.getFechaCreacion()).isNotNull();
    }
}