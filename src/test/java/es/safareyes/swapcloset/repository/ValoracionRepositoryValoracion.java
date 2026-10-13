package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Perfil;
import es.safareyes.swapcloset.modelo.Solicitud;
import es.safareyes.swapcloset.modelo.Valoracion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ValoracionRepositoryTest {

    @Autowired
    private ValoracionRepository valoracionRepository;
    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Test
    void guardaUnaValoracion() {
        Perfil perfil = perfilRepository.findAll().get(0);

        Solicitud s = new Solicitud();
        s.setPerfil(perfil);
        s.setTipoSolicitud("intercambio");
        solicitudRepository.saveAndFlush(s);

        Valoracion v = new Valoracion();
        v.setPerfil(perfil);
        v.setSolicitud(s);
        v.setPuntuacion((short) 5);
        v.setComentario("Todo perfecto");

        Valoracion guardada = valoracionRepository.saveAndFlush(v);

        assertThat(guardada.getId_valoracion()).isNotNull();
        assertThat(guardada.getFecha()).isNotNull();
    }
}