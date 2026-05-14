package kr.co.springbootex.ecommerce.dto.request.user;

import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;

public record UserUpdateRequest(
        @NotNull Long id,
        String password,
        String email,
        String telNo
) implements IdRequest<Long> {
}
