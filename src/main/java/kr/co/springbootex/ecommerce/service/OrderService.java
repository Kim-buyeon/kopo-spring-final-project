package kr.co.springbootex.ecommerce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.springbootex.ecommerce.dto.request.order.OrderCreateRequest;
import kr.co.springbootex.ecommerce.dto.response.IdResult;
import kr.co.springbootex.ecommerce.dto.response.order.DailyOrderStatus;
import kr.co.springbootex.ecommerce.dto.response.order.OrderCompleteResponse;
import kr.co.springbootex.ecommerce.dto.response.order.UserOrderStats;
import kr.co.springbootex.ecommerce.dto.response.quantity.QuantityListResponse;
import kr.co.springbootex.ecommerce.entity.OrderItem;
import kr.co.springbootex.ecommerce.entity.Orders;
import kr.co.springbootex.ecommerce.entity.Product;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.mapper.OrderMapper;
import kr.co.springbootex.ecommerce.repository.OrderItemRepository;
import kr.co.springbootex.ecommerce.repository.OrderRepository;

@Service
public class OrderService extends OrderFlowCommonService<Orders, String> {

	private final OrderMapper mapper;
	private final OrderRepository orderRepository;
	private final UserService userService;
	private final ProductService productService;
	private final OrderItemRepository orderItemRepository;
	public OrderService(OrderRepository orderRepository,
			OrderMapper mapper,
			UserService userService,
			ProductService productService,
			OrderItemRepository orderItemRepository) {
		super(orderRepository);
		this.mapper = mapper;
		this.orderRepository = orderRepository;
		this.userService = userService;
		this.productService = productService;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	protected IdResult<String> convertToIdResult(Orders order) {
		return OrderCompleteResponse.from(order);
	}
	
	public OrderCompleteResponse createOrder(OrderCreateRequest request, String userId) {
		User user = userService.getOneOrThrow(userId);
		List<OrderItem> orderItems  = request.getITems().stream()
									.map(dto ->{
										Product product = productService.getOneOrThrow(dto.id());
										product.substractStock(dto.quantity());
										return OrderItem.builder()
												.product(product)
												.quantity(dto.quantity())
												.price(product.getPrice())
												.build();
									})
									.toList();
		Orders order = Orders.builder()
						     .ordererName(user.getName())
						     .address(request.address())
						     .orderDate(LocalDate.now())
						     .orderItems(orderItems)
						     .build();
		orderItems.forEach(item -> item.setOrder(order));
		IdResult<String> savedResult = this.create(order);
		return (OrderCompleteResponse) savedResult;
	}
	
	//특정 주문 번호에 관한 목록 조회
	public QuantityListResponse<String> getOrderDetail(String orderId, String userId){
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
		return QuantityListResponse.of(orderItems);
	}
	
	//[관리자]가 일별 통계를 조회할 떄 사용하는 메서드
	public List<DailyOrderStatus> getDailyStats(){
		return mapper.countAndGroupByDaily();
	}
	
	//[관리자]가 사용자별 주문량을 볼 때 사용하는 메서드
	public List<UserOrderStats> getOrderStats(String userId){
		return mapper.countAndGroupByUserId(userId);
	}

}
