package kr.co.springbootex.ecommerce.dto.request.product;

import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;

public record ProductUpdateRequest(
    @NotNull Long id,
    String name,
    int price,
    int stock
) implements IdRequest<Long> {}
