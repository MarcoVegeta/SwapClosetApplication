package es.safareyes.swapcloset.mapper;

import es.safareyes.swapcloset.dto.request.CategoriaRequest;
import es.safareyes.swapcloset.dto.response.CategoriaResponse;
import es.safareyes.swapcloset.modelo.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoriaMapper {

    @Mapping(target = "id", source = "id_categoria")
    CategoriaResponse aResponse(Categoria categoria);

    List<CategoriaResponse> aResponseList(List<Categoria> categorias);

    @Mapping(target = "id_categoria", ignore = true)
    Categoria aEntidad(CategoriaRequest peticion);

    @Mapping(target = "id_categoria", ignore = true)
    void actualizar(@MappingTarget Categoria destino, CategoriaRequest peticion);
}