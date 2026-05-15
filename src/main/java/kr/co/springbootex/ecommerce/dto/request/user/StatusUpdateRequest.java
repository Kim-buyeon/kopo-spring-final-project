package kr.co.springbootex.ecommerce.dto.request.user;

import jakarta.validation.constraints.NotNull;
import kr.co.springbootex.ecommerce.entity.constant.UserStatus;

public record StatusUpdateRequest(
  @NotNull
  String id,
  @NotNull
  UserStatus userStatus
) {
}
