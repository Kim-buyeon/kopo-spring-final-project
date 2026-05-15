package kr.co.springbootex.ecommerce.util.exception;

import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult<Void>> handleBusinessException(BusinessException e){
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResult.fail(e.getErrorCode().getMessage()));
    }
}
