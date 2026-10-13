package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void guardaUnUsuarioConRolPorDefecto() {
        Usuario u = new Usuario();
        u.setUsername("usuario_test" + System.nanoTime());
        u.setContrasena("1234");

        Usuario guardado = usuarioRepository.saveAndFlush(u);

        assertThat(guardado.getId_usuario()).isNotNull();
        assertThat(guardado.getRol()).isEqualTo("usuario");
    }
}
