package kr.co.springbootex.ecommerce.dto.request.order;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import kr.co.springbootex.ecommerce.dto.request.ListHolder;

public record OrderCreateRequest(
    String address,
    @NotEmpty(message = "주문할 상품이 최소 하나는 있어야 합니다.")
    List<OrderItemRequest> orderItems
) implements ListHolder<OrderItemRequest>{

	@Override
	public List<OrderItemRequest> getITems() {
		return this.orderItems;
	}}
