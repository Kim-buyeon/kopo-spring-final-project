package kr.co.springbootex.ecommerce.dto.response.order;

import kr.co.springbootex.ecommerce.dto.response.IdResult;
import kr.co.springbootex.ecommerce.entity.Orders;

public record OrderCompleteResponse(
		String id,
		String ordererName,
		int amount,
		String orderStatus,
		String message
) implements IdResult<String>{
	//정적 팩토리 메서드
	public static OrderCompleteResponse from(Orders order) {
		return new OrderCompleteResponse(
				order.getId(),
				order.getOrdererName(),
				order.getAmount(),
				order.getStatus(),
				"주문이 성공적으로 완료되었습니다."
				);
				
	}
}
