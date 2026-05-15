package kr.co.springbootex.ecommerce.util.exception;


import kr.co.springbootex.ecommerce.util.exception.constant.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
