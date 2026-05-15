package kr.co.springbootex.ecommerce.dto.request.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;
import java.util.List;

public record OrderCreateRequest(
    @NotNull
    String id,//사용자 아이디
    String address,

    @NotEmpty(message = "주문할 상품이 최소 하나는 있어야 합니다.")
    List<OrderItemRequest> orderItems
) implements IdRequest<String> {}
