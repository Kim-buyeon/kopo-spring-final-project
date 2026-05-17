package kr.co.springbootex.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import kr.co.springbootex.ecommerce.entity.Orders;
import kr.co.springbootex.ecommerce.entity.User;

@Repository
public interface OrderRepository extends GenericRepository<Orders, String>{

    Page<Orders> findByUser(User user, Pageable pageable);
}
