package kr.co.springbootex.ecommerce.dto.request.user;

import kr.co.springbootex.ecommerce.dto.request.NameRequest;

public record UserUpdateRequest(
        String password,
        String email,
        String telNo,
        String name
) implements NameRequest {
}
