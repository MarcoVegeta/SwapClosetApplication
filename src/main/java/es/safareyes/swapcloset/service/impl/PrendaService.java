package es.safareyes.swapcloset.service;

import es.safareyes.swapcloset.dto.request.PrendaRequest;
import es.safareyes.swapcloset.dto.response.PaginaResponse;
import es.safareyes.swapcloset.dto.response.PrendaResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface PrendaService {

    List<PrendaResponse> listar(String titulo);

    PrendaResponse buscarPorId(Integer id);

    PrendaResponse crear(PrendaRequest peticion);

    PrendaResponse actualizar(Integer id, PrendaRequest peticion);

    PaginaResponse<PrendaResponse> listar(String titulo, Pageable pageable);

    void eliminar(Integer id);
}