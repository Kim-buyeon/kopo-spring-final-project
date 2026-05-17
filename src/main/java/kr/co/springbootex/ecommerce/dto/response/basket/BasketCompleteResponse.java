package kr.co.springbootex.ecommerce.dto.response.basket;

import kr.co.springbootex.ecommerce.dto.response.IdResult;
import kr.co.springbootex.ecommerce.entity.Basket;

public record BasketCompleteResponse(
		Long id,
		String userName,
		String message
) implements IdResult<Long> {
	public static BasketCompleteResponse from(Basket basket) {
		return new BasketCompleteResponse(
				basket.getId(),
				basket.getUser().getName(),
				"장바구니에 성공적으로 담겼습니다."
				);
				
	}
	
}
