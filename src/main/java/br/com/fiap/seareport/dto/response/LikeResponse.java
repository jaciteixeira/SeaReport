package br.com.fiap.seareport.dto.response;

import lombok.Builder;

@Builder
public record LikeResponse(
        String username,
        String contentPost
) {
}
