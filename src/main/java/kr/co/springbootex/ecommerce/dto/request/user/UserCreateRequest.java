package kr.co.springbootex.ecommerce.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import kr.co.springbootex.ecommerce.dto.request.NameRequest;

public record UserCreateRequest(
        @NotBlank String userId,
        @NotBlank String passsword,
        @NotBlank String name,
        String email,
        String telNo
) implements NameRequest {}
