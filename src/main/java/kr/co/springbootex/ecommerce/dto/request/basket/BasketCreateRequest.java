package kr.co.springbootex.ecommerce.dto.request.basket;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.IdRequest;
import kr.co.springbootex.ecommerce.entity.User;

import java.util.List;

public record BasketCreateRequest(
        @NotNull
        String id,//사용자 아이디
        @NotEmpty(message = "장바구니에 담을 상품이 최소 하나는 있어야 합니다")
        List<BasketItemRequest> basketItems
) implements IdRequest<String> {
}
