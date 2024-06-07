package br.com.fiap.seareport.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserRequest(

        @NotBlank(message = "O email é obrigatório!")
        @NotBlank
        String username,
        String phoneNumber,
        Integer xp,
        @NotNull
        AuthRequest auth

) {
}
