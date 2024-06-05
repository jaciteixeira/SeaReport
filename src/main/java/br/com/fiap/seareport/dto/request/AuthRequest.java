package br.com.fiap.seareport.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequest(
        @NotBlank @NotNull
        String id,
        @NotBlank @NotNull
        String email
) {
}
