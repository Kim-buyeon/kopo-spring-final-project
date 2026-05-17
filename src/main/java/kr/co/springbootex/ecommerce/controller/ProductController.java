package kr.co.springbootex.ecommerce.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import kr.co.springbootex.ecommerce.dto.request.product.ProductCreateRequest;
import kr.co.springbootex.ecommerce.dto.request.product.ProductStockUpdateRequest;
import kr.co.springbootex.ecommerce.dto.request.product.ProductUpdateRequest;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import kr.co.springbootex.ecommerce.dto.response.product.ProductResponse;
import kr.co.springbootex.ecommerce.dto.response.product.ProductStockUpdateResponse;
import kr.co.springbootex.ecommerce.entity.Product;
import kr.co.springbootex.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController extends NameAbleCommonController<
	Product, 
	String,
	ProductCreateRequest, 
	ProductUpdateRequest, 
	ProductResponse>{

	private final ProductService productService;
	public ProductController(final ProductService productService) {
		super(productService);
		this.productService = productService;
	}
	@Override
	protected ProductResponse convertToResponse(Product product) {
		return ProductResponse.from(product);
	}
	@Override
	protected Product convertToEntity(ProductCreateRequest request) {
		return Product.builder()
				.description(request.description())
				.name(request.name())
				.startDate(request.startDate())
				.endDate(request.endDate())
				.stock(request.stock())
				.price(request.price())
				.build();
	}
	@Override
	protected void updateEntityFromDto(ProductUpdateRequest request, Product product) {
		product.setName(request.name());
		product.setPrice(request.price());
		product.setStock(request.stock());
		product.setStartDate(request.startDate());
		product.setEndDate(request.endDate());
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResult<ProductResponse> create(
			@RequestPart(value = "request") @Valid ProductCreateRequest request,
			@RequestPart(value = "file", required = false) MultipartFile file
	){
		Product savedProduct = productService.createProductWithFile(request, file);
		return ApiResult.success(convertToResponse(savedProduct));
	}
	
	@PatchMapping("/{id}/inbound")
	public ApiResult<ProductStockUpdateResponse> inboundStock(
			@PathVariable("id")String id,
			@RequestBody @Valid ProductStockUpdateRequest request){
		ProductStockUpdateResponse response = productService.inboundProductStock(id, request);
		return ApiResult.success(response);
	}
	
	@PatchMapping("/{id}/outbound")
	public ApiResult<ProductStockUpdateResponse> outboundStock(
			@PathVariable("id")String id,
			@RequestBody @Valid ProductStockUpdateRequest request){
		ProductStockUpdateResponse response = productService.outboundProductStock(id, request);
		return ApiResult.success(response);
		
	}
	

}
