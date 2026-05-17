package kr.co.springbootex.ecommerce.dto.request.product;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import kr.co.springbootex.ecommerce.dto.request.NameRequest;

public record ProductCreateRequest(
        @NotBlank String name,
        String description,
        @Positive int price,
        @Positive int stock,
        LocalDate startDate,
        LocalDate endDate,
        MultipartFile file
) implements NameRequest {
}
