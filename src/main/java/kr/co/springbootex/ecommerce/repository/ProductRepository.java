package kr.co.springbootex.ecommerce.repository;

import org.springframework.stereotype.Repository;

import kr.co.springbootex.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends NameableRepository<Product, String>{
	
}
