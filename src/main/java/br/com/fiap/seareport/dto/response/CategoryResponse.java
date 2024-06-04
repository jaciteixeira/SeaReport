package br.com.fiap.seareport.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        String description
        ) {
}
