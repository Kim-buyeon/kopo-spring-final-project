package kr.co.springbootex.ecommerce.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.springbootex.ecommerce.dto.request.order.OrderCreateRequest;
import kr.co.springbootex.ecommerce.dto.request.order.OrderUpdateRequest;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import kr.co.springbootex.ecommerce.dto.response.order.DailyOrderStatus;
import kr.co.springbootex.ecommerce.dto.response.order.OrderCompleteResponse;
import kr.co.springbootex.ecommerce.dto.response.order.UserOrderStats;
import kr.co.springbootex.ecommerce.dto.response.quantity.QuantityListResponse;
import kr.co.springbootex.ecommerce.entity.OrderItem;
import kr.co.springbootex.ecommerce.entity.Orders;
import kr.co.springbootex.ecommerce.entity.Product;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.service.OrderService;
import kr.co.springbootex.ecommerce.service.ProductService;
import kr.co.springbootex.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends OrderFlowCommonController<
    Orders, 
    String, 
    OrderCreateRequest, 
    OrderUpdateRequest, 
    OrderCompleteResponse
> {

	private final OrderService orderService;
	private final UserService userService;
	private final ProductService productService;

	public OrderController(OrderService orderService, UserService userService, ProductService productService) {
		super(orderService);
		this.orderService = orderService;
		this.userService = userService;
		this.productService = productService;
	}

	@Override
	protected OrderCompleteResponse convertToResponse(Orders entity) {
		return OrderCompleteResponse.from(entity);
	}

	@Override
	protected List<Orders> convertToEntity(OrderCreateRequest request) {
		String currentUserId = extractUserIdFromSession();
		User user = userService.getOneOrThrow(currentUserId);
		
		List<OrderItem> orderItems = request.getITems().stream()
				.<OrderItem>map(dto -> {
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
		
		return List.of(order); 
	}

	@Override
	protected void updateEntityFromDto(OrderUpdateRequest request) {
	}


	@Override
	@PostMapping
	public ApiResult<OrderCompleteResponse> create(@RequestBody OrderCreateRequest request) {
		List<Orders> entities = convertToEntity(request);
		
		if (entities.isEmpty()) {
			throw new IllegalArgumentException("저장할 주문 내역이 존재하지 않습니다.");
		}
		
		Orders targetOrder = entities.get(0);
		orderService.create(targetOrder); 
		
		return ApiResult.success(convertToResponse(targetOrder));
	}

	@GetMapping("/{id}/details")
	public ApiResult<QuantityListResponse<String>> getOrderDetail(@PathVariable("id") String orderId) {
		String currentUserId = extractUserIdFromSession();
		
		return ApiResult.success(orderService.getOrderDetail(orderId, currentUserId));
	}


	@GetMapping("/admin/stats/daily")
	public ApiResult<List<DailyOrderStatus>> getDailyStats() {
		return ApiResult.success(orderService.getDailyStats());
	}

	@GetMapping("/admin/stats/users/{userId}")
	public ApiResult<List<UserOrderStats>> getUserOrderStats(@PathVariable("userId") String userId) {
		return ApiResult.success(orderService.getOrderStats(userId));
	}

	private String extractUserIdFromSession() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("LOGIN_USER") == null) {
			throw new IllegalArgumentException("세션이 만료되었거나 로그인이 필요한 서비스입니다.");
		}
		
		return (String) session.getAttribute("LOGIN_USER");
	}
}