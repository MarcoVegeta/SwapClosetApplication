package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Estilo;
import es.safareyes.swapcloset.modelo.Perfil;
import es.safareyes.swapcloset.modelo.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PerfilRepositoryTest {

    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstiloRepository estiloRepository;

    private Usuario crearUsuario() {
        Usuario u = new Usuario();
        u.setUsername("perfil_test" + System.nanoTime());
        u.setContrasena("1234");
        return usuarioRepository.saveAndFlush(u);
    }

    @Test
    void guardaUnPerfilConSuUsuario() {
        Perfil p = new Perfil();
        p.setUsuario(crearUsuario());
        p.setNombre("Test");
        p.setEmail("perfil" + System.nanoTime() + "@mail.com");

        Perfil guardado = perfilRepository.saveAndFlush(p);

        assertThat(guardado.getId_perfil()).isNotNull();
    }

    @Test
    void guardaUnPerfilConUnEstilo() {
        Estilo e = new Estilo();
        e.setNombre("Vintage" + System.nanoTime());
        estiloRepository.saveAndFlush(e);

        Perfil p = new Perfil();
        p.setUsuario(crearUsuario());
        p.setEmail("estilo" + System.nanoTime() + "@mail.com");
        p.getEstilos().add(e);

        Perfil guardado = perfilRepository.saveAndFlush(p);

        assertThat(guardado.getEstilos()).hasSize(1);
    }
}