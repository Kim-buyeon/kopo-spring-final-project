package kr.co.springbootex.ecommerce.dto.response;

import kr.co.springbootex.ecommerce.entity.base.Quantifiable;

public record QuantityResponse<T extends Quantifiable>(
        Long id,
        String productName,
        int quantity,
        int price
) {
    public static <T extends Quantifiable> QuantityResponse<T> from(Long id, T entity) {
        return new QuantityResponse<>(
                id,
                entity.getProduct().getName(),
                entity.getQuantity(),
                entity.getProduct().getPrice()
        );
    }
}
