package kr.co.springbootex.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

public interface NameRequest {
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    String name();
}
