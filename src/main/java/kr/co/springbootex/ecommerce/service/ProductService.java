package kr.co.springbootex.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.springbootex.ecommerce.dto.request.product.ProductCreateRequest;
import kr.co.springbootex.ecommerce.dto.request.product.ProductStockUpdateRequest;
import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.dto.response.product.ProductResponse;
import kr.co.springbootex.ecommerce.dto.response.product.ProductStockUpdateResponse;
import kr.co.springbootex.ecommerce.entity.Content;
import kr.co.springbootex.ecommerce.entity.Product;
import kr.co.springbootex.ecommerce.repository.ProductRepository;

@Service
public class ProductService extends NameAbleCommonService<Product, String> {

	public ProductService(ProductRepository productRepository) {
		super(productRepository);
	}
	
	@Override
	protected CommonInfoResponse<String, Product> convertToResponse(Product product) {
		return ProductResponse.from(product);
	}
	
	@Transactional
    public Product createProductWithFile(ProductCreateRequest dto, MultipartFile multipartFile) {
        
        Product product = Product.builder()
                .name(dto.name())
                .price(dto.price())
                .stock(dto.stock())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .build();

        if (multipartFile != null && !multipartFile.isEmpty()) {
            
            String orgFileName = multipartFile.getOriginalFilename();
            String extension = orgFileName.substring(orgFileName.lastIndexOf(".") + 1);
            
            String sFileName = UUID.randomUUID().toString() + "." + extension;
            String filePath = "C:/ecommerce/upload/";
            try {
                File targetFile = new File(filePath + sFileName);
                multipartFile.transferTo(targetFile);
            } catch (IOException e) {
                throw new RuntimeException("물리 파일 저장 중 에러가 발생했습니다. 폴더 경로를 확인하세요.", e);
            }

            Content content = Content.builder()
                    .name(orgFileName)
                    .sFileName(sFileName)
                    .filePath(filePath)
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .extension(extension)
                    .createdAt(LocalDateTime.now())
                    .build();

            content.setProduct(product);
            product.setContent(content); 
        }

        return nameableRepository.save(product);
    }
	
	//[관리자]가 재고 추가할 때 사용하는 메서드
	@Transactional
	public ProductStockUpdateResponse inboundProductStock(String productId, 
			ProductStockUpdateRequest request) {
		Product product = getOneOrThrow(productId);
		product.addStock(request.stock());
		return new ProductStockUpdateResponse(
				product.getStock(),
				"재고 수량이 성공적으로 추가되었습니다"); 
	}
	
	//[관리자]가 재고 뺄 때 사용하는 메서드
	@Transactional
	public ProductStockUpdateResponse outboundProductStock(String productId,
			ProductStockUpdateRequest request) {
		Product product = getOneOrThrow(productId);
		product.substractStock(request.stock());
		return new ProductStockUpdateResponse(
				product.getStock(),
				"재고 수량이 성공적으로 줄어들었습니다"
				);
	}

}
