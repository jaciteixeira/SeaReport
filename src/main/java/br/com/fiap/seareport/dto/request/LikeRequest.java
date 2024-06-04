package br.com.fiap.seareport.dto.request;

import jakarta.validation.constraints.NotNull;

public record LikeRequest(
        @NotNull
        Long idUser,
        @NotNull
        Long idPost
) {
}
