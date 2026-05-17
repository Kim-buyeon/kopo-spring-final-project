package kr.co.springbootex.ecommerce.dto.response.product;

public record ProductStockUpdateResponse(
		int stock,
		String message
		) {}
