package kr.co.springbootex.ecommerce.dto.response;

import kr.co.springbootex.ecommerce.entity.base.Nameable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CommonInfoResponse<ID, T extends Nameable> {
    private ID id;
    private String name;
}
