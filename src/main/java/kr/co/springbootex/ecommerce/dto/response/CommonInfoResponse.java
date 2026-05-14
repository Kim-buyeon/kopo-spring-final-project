package kr.co.springbootex.ecommerce.dto.response;

import kr.co.springbootex.ecommerce.entity.base.Nameable;

public record CommonInfoResponse<ID, T extends Nameable>(
        ID id,
        String name
) {
    //정적 팩토리 메서드
    public static <ID, T extends Nameable> CommonInfoResponse<ID, T> from(ID id, T entity) {
        return new CommonInfoResponse<>(id, entity.getName());
    }
}
