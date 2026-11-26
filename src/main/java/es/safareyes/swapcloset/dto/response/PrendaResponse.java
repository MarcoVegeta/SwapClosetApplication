package es.safareyes.swapcloset.dto.response;

import java.time.LocalDateTime;

public record PrendaResponse(
        Integer id,
        Integer idPerfil,
        String categoria,
        String titulo,
        String descripcion,
        String marca,
        String talla,
        String estado,
        Boolean disponibilidad,
        Boolean permitePrestamo,
        Boolean permiteIntercambio,
        LocalDateTime fechaPublicacion
) { }