package br.com.fiap.seareport.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserRequestLogin(
        @NotNull
        String username,
        @NotNull
        String password
) {
}
