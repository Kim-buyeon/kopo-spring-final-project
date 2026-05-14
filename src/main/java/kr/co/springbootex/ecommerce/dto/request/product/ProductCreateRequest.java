package kr.co.springbootex.ecommerce.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import kr.co.springbootex.ecommerce.dto.request.NameRequest;

public record ProductCreateRequest(
        @NotBlank String name,
        String description,
        @Positive int price,
        @Positive int stock
) implements NameRequest {
}
