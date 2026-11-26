package es.safareyes.swapcloset.service.impl;

import es.safareyes.swapcloset.dto.request.CategoriaRequest;
import es.safareyes.swapcloset.dto.response.CategoriaResponse;
import es.safareyes.swapcloset.exception.ConflictoException;
import es.safareyes.swapcloset.exception.RecursoNoEncontradoException;
import es.safareyes.swapcloset.mapper.CategoriaMapper;
import es.safareyes.swapcloset.modelo.Categoria;
import es.safareyes.swapcloset.repository.CategoriaRepository;
import es.safareyes.swapcloset.repository.PrendaRepository;
import es.safareyes.swapcloset.service.impl.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final PrendaRepository prendaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaResponse> listar() {
        return categoriaMapper.aResponseList(categoriaRepository.findAll());
    }

    @Override
    public CategoriaResponse buscarPorId(Integer id) {
        return categoriaMapper.aResponse(buscarEntidad(id));
    }

    @Override
    @Transactional
    public CategoriaResponse crear(CategoriaRequest peticion) {
        if (categoriaRepository.existsByNombreIgnoreCase(peticion.nombre())) {
            throw new ConflictoException("Ya existe la categoría «" + peticion.nombre() + "»");
        }

        Categoria guardada = categoriaRepository.save(categoriaMapper.aEntidad(peticion));
        log.info("Categoría creada: id={}", guardada.getId_categoria());

        return categoriaMapper.aResponse(guardada);
    }

    @Override
    @Transactional
    public CategoriaResponse actualizar(Integer id, CategoriaRequest peticion) {
        Categoria categoria = buscarEntidad(id);

        if (categoriaRepository.contarNombreExcepto(peticion.nombre(), id) > 0) {
            throw new ConflictoException("Ya existe otra categoría «" + peticion.nombre() + "»");
        }

        categoriaMapper.actualizar(categoria, peticion);
        return categoriaMapper.aResponse(categoria);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        Categoria categoria = buscarEntidad(id);

        if (prendaRepository.contarPorCategoria(id) > 0) {
            throw new ConflictoException("No se puede eliminar una categoría que tiene prendas");
        }

        categoriaRepository.delete(categoria);
        log.info("Categoría eliminada: id={}", id);
    }

    private Categoria buscarEntidad(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la categoría con id " + id));
    }
}