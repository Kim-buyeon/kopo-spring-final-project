package kr.co.springbootex.ecommerce.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kr.co.springbootex.ecommerce.entity.User;

@Repository
public interface UserRepository extends NameableRepository<User,String> {

    Optional<User> findById(String id);
    boolean existsByEmail(String email);
    User findByIdAndPassword(String id, String password);
}
