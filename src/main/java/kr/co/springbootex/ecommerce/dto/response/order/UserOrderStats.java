package kr.co.springbootex.ecommerce.dto.response.order;

import java.time.LocalDate;

public record UserOrderStats(
		String idUser,
		long totalOrders,
		String nmDeliveryAddress,
		int totalAmount,
		LocalDate daOrder
		) {}
