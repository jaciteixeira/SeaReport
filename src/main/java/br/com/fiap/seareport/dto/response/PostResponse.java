package br.com.fiap.seareport.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponse(
        Long id,
        String contentPost,
        LocalDateTime date
) {
}
