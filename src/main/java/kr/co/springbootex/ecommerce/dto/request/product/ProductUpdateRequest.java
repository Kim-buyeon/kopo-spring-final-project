package kr.co.springbootex.ecommerce.dto.request.product;

import java.time.LocalDate;

import kr.co.springbootex.ecommerce.dto.request.NameRequest;

public record ProductUpdateRequest(
    String name,
    int price,
    int stock,
    LocalDate startDate,
    LocalDate endDate
) implements NameRequest{}
