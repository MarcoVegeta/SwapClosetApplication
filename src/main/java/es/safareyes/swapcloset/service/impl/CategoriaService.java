package es.safareyes.swapcloset.service.impl;

import es.safareyes.swapcloset.dto.request.CategoriaRequest;
import es.safareyes.swapcloset.dto.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {

    List<CategoriaResponse> listar();

    CategoriaResponse buscarPorId(Integer id);

    CategoriaResponse crear(CategoriaRequest peticion);

    CategoriaResponse actualizar(Integer id, CategoriaRequest peticion);

    void eliminar(Integer id);
}