package br.com.fiap.seareport.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String id,
        String email
) {
}
