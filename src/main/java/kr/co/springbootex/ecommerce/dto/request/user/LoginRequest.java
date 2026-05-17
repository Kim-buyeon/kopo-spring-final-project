package kr.co.springbootex.ecommerce.dto.request.user;

import kr.co.springbootex.ecommerce.dto.request.IdRequest;


public record LoginRequest(
		String id,
		String password
) implements IdRequest<String> {}
