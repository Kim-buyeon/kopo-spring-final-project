package kr.co.springbootex.ecommerce.dto.response.order;

import java.time.LocalDate;

public record DailyOrderStatus(
	LocalDate daOrder,
	long pureUserCount
) {}
