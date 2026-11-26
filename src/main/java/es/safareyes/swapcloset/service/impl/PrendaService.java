package es.safareyes.swapcloset.service.impl;

import es.safareyes.swapcloset.dto.request.PrendaRequest;
import es.safareyes.swapcloset.dto.response.PaginaResponse;
import es.safareyes.swapcloset.dto.response.PrendaResponse;
import org.springframework.data.domain.Pageable;

public interface PrendaService {

    PaginaResponse<PrendaResponse> listar(String titulo, Pageable pageable);

    PrendaResponse buscarPorId(Integer id);

    PrendaResponse crear(PrendaRequest peticion);

    PrendaResponse actualizar(Integer id, PrendaRequest peticion);

    void eliminar(Integer id);
}