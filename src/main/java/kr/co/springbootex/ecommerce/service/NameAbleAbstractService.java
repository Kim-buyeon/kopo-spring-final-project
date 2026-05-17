package kr.co.springbootex.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.entity.base.Nameable;

public interface NameAbleAbstractService<T extends Nameable<ID>, ID>{

    CommonInfoResponse<ID, T> create(T item);
    CommonInfoResponse<ID, T> update(T item);
    void remove(ID id);
    Optional<T> getOne(ID id);
    List<T> getAll(Pageable pageable);
}
