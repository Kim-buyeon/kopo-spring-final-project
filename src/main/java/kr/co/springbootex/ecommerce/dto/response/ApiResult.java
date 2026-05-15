package kr.co.springbootex.ecommerce.dto.response;

import lombok.Builder;

@Builder
public record ApiResult<T>(
        boolean success,
        T data,
        String error
) {
    //성공 응답을 위한 편의 메서드
    public static <T> ApiResult<T> success(T data){
        return ApiResult.<T>builder().
                success(true)
                .data(data)
                .error(null)
                .build();
    }

    //실패 응답을 위한 편의 메서드
    public static ApiResult<Void> fail(String errorMessage){
        return ApiResult. <Void>builder()
                .success(false)
                .data(null)
                .error(errorMessage)
                .build();
    }
}
