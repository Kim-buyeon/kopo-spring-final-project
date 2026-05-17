package kr.co.springbootex.ecommerce.repository;

import org.springframework.stereotype.Repository;

import kr.co.springbootex.ecommerce.entity.Category;
@Repository
public interface CategoryRepository extends NameableRepository<Category, Long>{
}
