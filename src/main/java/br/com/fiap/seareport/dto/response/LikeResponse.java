package br.com.fiap.seareport.dto.response;

import lombok.Builder;

@Builder
public record LikeResponse(

        Long id,
        String username,
        String contentPost
) {
}