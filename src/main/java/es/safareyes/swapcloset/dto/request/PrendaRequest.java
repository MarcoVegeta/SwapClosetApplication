package es.safareyes.swapcloset.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrendaRequest(

        @NotNull(message = "Hay que indicar el perfil que publica la prenda")
        Integer idPerfil,

        @NotNull(message = "Hay que indicar la categoría")
        Integer idCategoria,

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no puede pasar de 150 caracteres")
        String titulo,

        @Size(max = 2000, message = "La descripción no puede pasar de 2000 caracteres")
        String descripcion,

        @Size(max = 100, message = "La marca no puede pasar de 100 caracteres")
        String marca,

        @Size(max = 10, message = "La talla no puede pasar de 10 caracteres")
        String talla,

        @NotNull(message = "Hay que indicar si permite préstamo")
        Boolean permitePrestamo,

        @NotNull(message = "Hay que indicar si permite intercambio")
        Boolean permiteIntercambio
) { }