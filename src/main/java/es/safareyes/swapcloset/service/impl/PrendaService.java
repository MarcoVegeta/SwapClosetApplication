package es.safareyes.swapcloset.service;

import es.safareyes.swapcloset.dto.request.PrendaRequest;
import es.safareyes.swapcloset.dto.response.PrendaResponse;

import java.util.List;

public interface PrendaService {

    List<PrendaResponse> listar(String titulo);

    PrendaResponse buscarPorId(Integer id);

    PrendaResponse crear(PrendaRequest peticion);

    PrendaResponse actualizar(Integer id, PrendaRequest peticion);

    void eliminar(Integer id);
}