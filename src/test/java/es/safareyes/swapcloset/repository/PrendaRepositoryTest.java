package es.safareyes.swapcloset.repository;

import es.safareyes.swapcloset.modelo.Categoria;
import es.safareyes.swapcloset.modelo.Perfil;
import es.safareyes.swapcloset.modelo.Prenda;
import es.safareyes.swapcloset.modelo.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PrendaRepositoryTest {

    @Autowired
    private PrendaRepository prendaRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crea y guarda una prenda con todo lo que necesita
    private Prenda crearPrenda(String titulo, String marca, String nombreCategoria) {
        long n = System.nanoTime();

        Usuario u = new Usuario();
        u.setUsername("user" + n);
        u.setContrasena("1234");
        usuarioRepository.saveAndFlush(u);

        Perfil perfil = new Perfil();
        perfil.setUsuario(u);
        perfil.setEmail("mail" + n + "@test.com");
        perfilRepository.saveAndFlush(perfil);

        Categoria c = new Categoria();
        c.setNombre(nombreCategoria);
        categoriaRepository.saveAndFlush(c);

        Prenda p = new Prenda();
        p.setPerfil(perfil);
        p.setCategoria(c);
        p.setTitulo(titulo);
        p.setMarca(marca);
        return prendaRepository.saveAndFlush(p);
    }

    @Test
    void guardaUnaPrendaConValoresPorDefecto() {
        Prenda p = crearPrenda("Chaqueta test", "MarcaTest", "Cat" + System.nanoTime());

        assertThat(p.getId_prenda()).isNotNull();
        assertThat(p.getEstado()).isEqualTo("disponible");
        assertThat(p.getDisponibilidad()).isTrue();
        assertThat(p.getFechaPublicacion()).isNotNull();
    }

    @Test
    void encuentraLasPrendasDisponibles() {
        crearPrenda("Abrigo test", "MarcaTest", "Cat" + System.nanoTime());

        List<Prenda> prendas = prendaRepository.findByDisponibilidadTrue();

        assertThat(prendas).isNotEmpty();
        assertThat(prendas).allMatch(p -> p.getDisponibilidad());
    }

    @Test
    void encuentraPrendasPorMarca() {
        String marca = "Marca" + System.nanoTime();
        crearPrenda("Jersey test", marca, "Cat" + System.nanoTime());

        List<Prenda> prendas = prendaRepository.findByMarca(marca);

        assertThat(prendas).hasSize(1);
        assertThat(prendas.get(0).getTitulo()).isEqualTo("Jersey test");
    }

    @Test
    void buscaPorTituloSinMirarMayusculas() {
        crearPrenda("Bufanda Roja Test", "MarcaTest", "Cat" + System.nanoTime());

        List<Prenda> prendas = prendaRepository.findByTituloContainingIgnoreCase("BUFANDA ROJA");

        assertThat(prendas).isNotEmpty();
    }

    @Test
    void buscaPrendasPorCategoria() {
        String categoria = "Cat" + System.nanoTime();
        crearPrenda("Gorro test", "MarcaTest", categoria);

        List<Prenda> prendas = prendaRepository.buscarPorCategoria(categoria);

        assertThat(prendas).hasSize(1);
        assertThat(prendas.get(0).getTitulo()).isEqualTo("Gorro test");
    }
}