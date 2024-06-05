package br.com.fiap.seareport.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserRequest(

//        @NotBlank
//        String name,
        @NotBlank(message = "O email é obrigatório!")
//        @Email(message = "Email inválido!")
//        String email,
        @NotBlank
        String username,
        @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}")
        @NotBlank
        @NotNull(message = "a senha é obrigatório!")
        String password,
        String phoneNumber,
        Integer xp,
        @NotNull
        AuthRequest auth

) {
}
