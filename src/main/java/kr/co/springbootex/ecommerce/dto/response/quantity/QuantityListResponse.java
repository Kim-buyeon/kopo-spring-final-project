package kr.co.springbootex.ecommerce.dto.response.quantity;

import kr.co.springbootex.ecommerce.entity.base.Quantifiable;
import java.util.List;

public record QuantityListResponse<ID>(
    List<QuantityResponse<ID>> items,
    int totalPrice,
    int totalQuantity
) {
    public static <ID, T extends Quantifiable<ID>> QuantityListResponse<ID> of(List<T> entities) {
        
        List<QuantityResponse<ID>> dtoList = entities.stream()
            .map(e -> QuantityResponse.<ID, T> from(e)) 
            .toList();
        
        int totalQuantity = entities.stream()
                .mapToInt(Quantifiable::getQuantity)
                .sum();
        
        int totalPrice = entities.stream()
                .mapToInt(e -> e.getPrice() * e.getQuantity())
                .sum();
                
        return new QuantityListResponse<>(dtoList, totalPrice, totalQuantity);
    }
}