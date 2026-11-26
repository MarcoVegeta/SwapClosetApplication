package es.safareyes.swapcloset.service.impl;

import es.safareyes.swapcloset.dto.request.PrendaRequest;
import es.safareyes.swapcloset.dto.response.PaginaResponse;
import es.safareyes.swapcloset.dto.response.PrendaResponse;
import es.safareyes.swapcloset.exception.ConflictoException;
import es.safareyes.swapcloset.exception.RecursoNoEncontradoException;
import es.safareyes.swapcloset.exception.ReglaNegocioException;
import es.safareyes.swapcloset.mapper.PrendaMapper;
import es.safareyes.swapcloset.modelo.Categoria;
import es.safareyes.swapcloset.modelo.Perfil;
import es.safareyes.swapcloset.modelo.Prenda;
import es.safareyes.swapcloset.repository.CategoriaRepository;
import es.safareyes.swapcloset.repository.PerfilRepository;
import es.safareyes.swapcloset.repository.PrendaRepository;
import es.safareyes.swapcloset.repository.SolicitudRepository;
import es.safareyes.swapcloset.service.PrendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PrendaServiceImpl implements PrendaService {

    private final PrendaRepository prendaRepository;
    private final PerfilRepository perfilRepository;
    private final CategoriaRepository categoriaRepository;
    private final SolicitudRepository solicitudRepository;
    private final PrendaMapper prendaMapper;

    @Override
    public PaginaResponse<PrendaResponse> listar(String titulo, Pageable pageable) {
        Page<Prenda> pagina = (titulo == null || titulo.isBlank())
                ? prendaRepository.findAll(pageable)
                : prendaRepository.findByTituloContainingIgnoreCase(titulo, pageable);

        return PaginaResponse.de(pagina.map(prendaMapper::aResponse));
    }

    @Override
    public PrendaResponse buscarPorId(Integer id) {
        return prendaMapper.aResponse(buscarEntidad(id));
    }

    @Override
    @Transactional   // esta sí escribe
    public PrendaResponse crear(PrendaRequest peticion) {

        if (prendaRepository.contarTituloEnPerfil(peticion.idPerfil(), peticion.titulo()) > 0) {
            throw new ConflictoException(
                    "Ya has publicado una prenda con el título «" + peticion.titulo() + "»");
        }

        Perfil perfil = perfilRepository.findById(peticion.idPerfil())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el perfil con id " + peticion.idPerfil()));

        Categoria categoria = buscarCategoria(peticion.idCategoria());

        comprobarModalidad(peticion);

        Prenda prenda = prendaMapper.aEntidad(peticion);
        prenda.setPerfil(perfil);
        prenda.setCategoria(categoria);

        Prenda guardada = prendaRepository.save(prenda);
        log.info("Prenda creada: id={} perfil={}", guardada.getId_prenda(), peticion.idPerfil());

        return prendaMapper.aResponse(guardada);
    }

    @Override
    @Transactional
    public PrendaResponse actualizar(Integer id, PrendaRequest peticion) {

        Prenda prenda = buscarEntidad(id);

        Integer idPerfil = prenda.getPerfil().getId_perfil();
        if (prendaRepository.contarTituloEnPerfilExcepto(idPerfil, peticion.titulo(), id) > 0) {
            throw new ConflictoException(
                    "Ya tienes otra prenda con el título «" + peticion.titulo() + "»");
        }

        Categoria categoria = buscarCategoria(peticion.idCategoria());
        comprobarModalidad(peticion);

        prendaMapper.actualizar(prenda, peticion);
        prenda.setCategoria(categoria);

        return prendaMapper.aResponse(prenda);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {

        Prenda prenda = buscarEntidad(id);

        if (solicitudRepository.contarPorPrenda(id) > 0) {
            throw new ConflictoException(
                    "No se puede eliminar una prenda que aparece en solicitudes");
        }

        prendaRepository.delete(prenda);
        log.info("Prenda eliminada: id={}", id);
    }

    private Prenda buscarEntidad(Integer id) {
        return prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la prenda con id " + id));
    }

    private Categoria buscarCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la categoría con id " + idCategoria));
    }

    private void comprobarModalidad(PrendaRequest peticion) {
        boolean prestamo = Boolean.TRUE.equals(peticion.permitePrestamo());
        boolean intercambio = Boolean.TRUE.equals(peticion.permiteIntercambio());

        if (!prestamo && !intercambio) {
            throw new ReglaNegocioException(
                    "La prenda tiene que permitir préstamo, intercambio o ambos");
        }
    }
}