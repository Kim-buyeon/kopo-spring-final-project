package kr.co.springbootex.ecommerce.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;

public record PasswordUpdateRequest(
        @NotBlank
        String id,
        @NotBlank
        String password
) implements IdRequest<String> {
}
