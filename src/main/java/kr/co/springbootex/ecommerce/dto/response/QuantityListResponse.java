package kr.co.springbootex.ecommerce.dto.response;

import kr.co.springbootex.ecommerce.entity.base.Quantifiable;

import java.util.List;

public record QuantityListResponse<T extends Quantifiable>(
    List<QuantityResponse<T>> items,
    int totalPrice,
    int totalQuantity
) {
    public static <T extends Quantifiable> QuantityListResponse<T> of(List<T> entities) {
        List<QuantityResponse<T>> dtoList = entities.stream()
                .map(e -> QuantityResponse.from(e.getId(), e))
                .toList();
        int totalQuantity = entities.stream().mapToInt(Quantifiable::getQuantity).sum();
        int totalPrice = entities.stream().mapToInt(e -> e.getPrice() * e.getQuantity()).sum();
        return new QuantityListResponse<>(dtoList, totalPrice,totalQuantity);
    }
}
