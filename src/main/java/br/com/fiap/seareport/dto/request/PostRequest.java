package br.com.fiap.seareport.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank
        String contentPost
) {
}
