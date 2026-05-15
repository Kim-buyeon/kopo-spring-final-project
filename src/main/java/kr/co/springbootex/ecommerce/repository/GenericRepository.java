package kr.co.springbootex.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {
}
