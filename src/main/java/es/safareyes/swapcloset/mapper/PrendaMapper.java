package es.safareyes.swapcloset.mapper;

import es.safareyes.swapcloset.dto.request.PrendaRequest;
import es.safareyes.swapcloset.dto.response.PrendaResponse;
import es.safareyes.swapcloset.modelo.Prenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrendaMapper {

    @Mapping(target = "id", source = "id_prenda")
    @Mapping(target = "idPerfil", source = "perfil.id_perfil")
    @Mapping(target = "categoria", source = "categoria.nombre")
    PrendaResponse aResponse(Prenda prenda);

    List<PrendaResponse> aResponseList(List<Prenda> prendas);

    @Mapping(target = "id_prenda", ignore = true)
    @Mapping(target = "perfil", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "disponibilidad", ignore = true)
    @Mapping(target = "fechaPublicacion", ignore = true)
    Prenda aEntidad(PrendaRequest peticion);

    @Mapping(target = "id_prenda", ignore = true)
    @Mapping(target = "perfil", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "disponibilidad", ignore = true)
    @Mapping(target = "fechaPublicacion", ignore = true)
    void actualizar(@MappingTarget Prenda destino, PrendaRequest peticion);
}