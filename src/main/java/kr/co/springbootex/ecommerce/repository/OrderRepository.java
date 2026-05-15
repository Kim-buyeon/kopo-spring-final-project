package kr.co.springbootex.ecommerce.repository;

import kr.co.springbootex.ecommerce.entity.Orders;
import kr.co.springbootex.ecommerce.entity.User;

public interface OrderRepository extends GenericRepository<Orders, String>{

    Orders findByUser(User user);
}
