package kr.co.springbootex.ecommerce.dto.request.basket;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import kr.co.springbootex.ecommerce.dto.request.ListHolder;

public record BasketCreateRequest(
        @NotEmpty(message = "장바구니에 담을 상품이 최소 하나는 있어야 합니다")
        List<BasketItemRequest> basketItems
) implements ListHolder<BasketItemRequest> {

	@Override
	public List<BasketItemRequest> getITems() {
		return this.basketItems;
	}}
