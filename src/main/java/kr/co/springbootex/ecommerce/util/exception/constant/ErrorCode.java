package kr.co.springbootex.ecommerce.util.exception.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 유저 관련 에러 코드 (U)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "존재하지 않는 사용자입니다."),
    DUPLICATE_USER(HttpStatus.CONFLICT, "U002", "이미 사용 중인 아이디입니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "U003", "아이디 또는 비밀번호가 일치하지 않습니다."),
    USER_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "U004", "(사용자) 유효하지 않은 요청입니다."),
    USER_DELETE_FAIL(HttpStatus.FORBIDDEN, "U005", "사용자를 삭제할 수 없습니다."), // 세미콜론 제거

    // 상품 관련 에러 코드 (P)
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "존재하지 않는 상품입니다."), // 알파벳 O -> 숫자 0으로 교정
    OUT_OF_STOCK(HttpStatus.CONFLICT, "P002", "상품 재고가 부족합니다."),
    PRODUCT_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "P003", "(상품) 유효하지 않은 요청입니다."),
    PRODUCT_DELETE_FAIL(HttpStatus.FORBIDDEN, "P004", "상품을 삭제할 수 없습니다."),

    // 주문 관련 (O)
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "O001", "주문 내역을 찾을 수 없습니다."),
    ORDER_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "O002", "(주문) 유효하지 않은 요청입니다."),

    // 장바구니 관련 (B)
    BASKET_NOT_FOUND(HttpStatus.NOT_FOUND, "B001", "장바구니 내역을 찾을 수 없습니다."),
    BASKET_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "B002", "(장바구니) 유효하지 않은 요청입니다."),

    // 카테고리 관련 (C)
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "카테고리를 찾을 수 없습니다."), // 알파벳 O -> 숫자 0으로 교정
    CATEGORY_INVALID_REQUEST(HttpStatus.BAD_REQUEST, "C002", "(카테고리) 유효하지 않은 요청입니다."),

    // 공통 / 시스템 관련 (S)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 내부 오류가 발생했습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "S002", "허용되지 않는 메소드입니다."); // 알파벳 O -> 숫자 0으로 교정

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    // HTTP 상태 코드 숫자값(예: 404)을 반환하는 편의 메서드
    public int getStatusValue() {
        return this.status.value();
    }
}