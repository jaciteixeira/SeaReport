package br.com.fiap.seareport.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String username,
        Integer xp,
        AuthResponse auth
) {
}
