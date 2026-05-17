package kr.co.springbootex.ecommerce.service;

import org.springframework.stereotype.Service;

import kr.co.springbootex.ecommerce.dto.response.IdResult;
import kr.co.springbootex.ecommerce.dto.response.basket.BasketCompleteResponse;
import kr.co.springbootex.ecommerce.entity.Basket;
import kr.co.springbootex.ecommerce.repository.BasketRepository;

@Service
public class BasketService extends OrderFlowCommonService<Basket, Long> {

	public BasketService(BasketRepository basketRepository) {
		super(basketRepository);
	}

	@Override
	protected IdResult<Long> convertToIdResult(Basket basket) {
		return BasketCompleteResponse.from(basket);
	}

}
