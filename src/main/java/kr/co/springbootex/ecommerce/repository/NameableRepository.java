package kr.co.springbootex.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import kr.co.springbootex.ecommerce.entity.base.Nameable;

@NoRepositoryBean
public interface NameableRepository<T extends Nameable<ID>, ID> extends GenericRepository<T, ID>{
	Optional<T> findByName(String name);
	Page<T> findAll(Pageable pageable);
}
