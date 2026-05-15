package kr.co.springbootex.ecommerce.dto.request.basket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;

public record BasketItemRequest(
    @NotNull
    String id,
    @Min(1) int quantity
) implements IdRequest<String> {
}
