package kr.co.springbootex.ecommerce.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import kr.co.springbootex.ecommerce.dto.request.NameRequest;

public record CategoryCreateRequest(
        @NotBlank
        String name,
        String sortOrder,
        String parentId
) implements NameRequest {}
