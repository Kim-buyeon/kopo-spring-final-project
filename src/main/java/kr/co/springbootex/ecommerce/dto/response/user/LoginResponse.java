package kr.co.springbootex.ecommerce.dto.response.user;

public record LoginResponse(
		String userName,
		String userClassification,
		String message
) {}
