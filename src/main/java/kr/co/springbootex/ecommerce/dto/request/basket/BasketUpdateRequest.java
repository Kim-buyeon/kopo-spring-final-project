package kr.co.springbootex.ecommerce.dto.request.basket;

import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;

public record BasketUpdateRequest(
    @NotNull
    String id,
    @NotNull(message = "업데이트 할 상품을 골라야 합니다.")
    BasketItemRequest basketItem
) implements IdRequest<String> {
}
