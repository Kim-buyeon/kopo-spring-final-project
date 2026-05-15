package kr.co.springbootex.ecommerce.repository;

import kr.co.springbootex.ecommerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String> {

    Page<User> findAll(Pageable pageable);
    Optional<User> findById(String id);
    boolean existsByEmail(String email);
}
