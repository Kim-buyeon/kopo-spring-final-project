package kr.co.springbootex.ecommerce.dto.request.order;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.UpdateHolder;

public record OrderUpdateRequest(
		@NotNull
		String id,
		@NotNull(message = "업데이트할 상품을 골라야합니다.")
		@Valid
		OrderItemRequest orderItem
)implements UpdateHolder<String, OrderItemRequest> {

	@Override
	public List<OrderItemRequest> getITems() {
		return List.of(this.orderItem);
	}}
