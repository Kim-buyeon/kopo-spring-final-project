package kr.co.springbootex.ecommerce.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;

public record CategoryUpdateRequest(
        @NotBlank
        Long id,
        String name
) implements IdRequest<Long> {
}
