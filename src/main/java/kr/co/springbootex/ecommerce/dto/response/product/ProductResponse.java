package kr.co.springbootex.ecommerce.dto.response.product;

import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.entity.Product;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProductResponse extends CommonInfoResponse<String, Product>{

    private final String description;
    private final int price;
    private final int stock;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public ProductResponse(String id,
                           String name,
                           String description,
                           int price,
                           int stock,
                           LocalDate startDate,
                           LocalDate endDate) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public static ProductResponse from(Product product) {
    	return new ProductResponse(
    			product.getId(),
    			product.getName(),
    			product.getDescription(),
    			product.getPrice(),
    			product.getStock(),
    			product.getStartDate(),
    			product.getEndDate()
    	);
    			
    }
}
