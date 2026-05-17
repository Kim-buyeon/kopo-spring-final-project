package kr.co.springbootex.ecommerce.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.dto.request.NameRequest;
import kr.co.springbootex.ecommerce.entity.constant.UserClassification;
import kr.co.springbootex.ecommerce.entity.constant.UserStatus;

public record UserCreateRequest(
        @NotBlank String userId,
        @NotBlank String password,
        @NotBlank String name,
        String email,
        String telNo,

        @NotNull(message = "사용자 권한은 필수입니다.")
        UserClassification userClassification,
        @NotNull(message = "사용자 상태는 필수입니다.")
        UserStatus userStatus

) implements NameRequest {}
