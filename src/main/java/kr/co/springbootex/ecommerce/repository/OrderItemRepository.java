package kr.co.springbootex.ecommerce.repository;

import java.util.List;

import kr.co.springbootex.ecommerce.entity.OrderItem;

public interface OrderItemRepository  extends GenericRepository<OrderItem, String>{
	List<OrderItem> findByOrderId(String orderId);

}
