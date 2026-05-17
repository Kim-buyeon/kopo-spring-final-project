package kr.co.springbootex.ecommerce.dto.response.quantity;

import kr.co.springbootex.ecommerce.entity.base.Quantifiable;

public record QuantityResponse<ID>(
		ID id,
        String productName,
        int quantity,
        int price
) {
    public static <ID, T extends Quantifiable<ID>> QuantityResponse<ID> from(T entity) {
        return new QuantityResponse<>(
                entity.getId(),
                entity.getProduct().getName(),
                entity.getQuantity(),
                entity.getProduct().getPrice()
        );
    }
}
