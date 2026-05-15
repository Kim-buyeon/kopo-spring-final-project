package kr.co.springbootex.ecommerce.repository;

import kr.co.springbootex.ecommerce.entity.Basket;
import kr.co.springbootex.ecommerce.entity.User;

public interface BasketRepository extends GenericRepository<Basket, Long>{

    Basket findByUser(User user);
}
