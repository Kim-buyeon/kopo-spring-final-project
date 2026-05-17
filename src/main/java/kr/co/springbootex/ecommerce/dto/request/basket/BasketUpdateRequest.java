package kr.co.springbootex.ecommerce.dto.request.basket;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.UpdateHolder;

public record BasketUpdateRequest(
    @NotNull
    Long id,//장바구니의 아이디
    
    @NotNull(message = "업데이트 할 상품을 골라야 합니다.")
    @Valid
    BasketItemRequest basketItem
) implements UpdateHolder<Long, BasketItemRequest> {

	@Override
	public List<BasketItemRequest> getITems() {
		return List.of(this.basketItem);
	}
}
