package kr.co.springbootex.ecommerce.service;

import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.entity.base.Nameable;

import java.util.List;
import java.util.Optional;

public interface NameAbleAbstractService<T extends Nameable, ID>{

    CommonInfoResponse<ID, T> create(T item);
    CommonInfoResponse<ID, T> update(T item);
    void remove(ID id);
    Optional<T> getOne(ID id);
    List<T> getAll();
}
