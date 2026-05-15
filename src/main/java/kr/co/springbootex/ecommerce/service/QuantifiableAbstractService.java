package kr.co.springbootex.ecommerce.service;

import kr.co.springbootex.ecommerce.dto.response.QuantityResponse;
import kr.co.springbootex.ecommerce.entity.base.Quantifiable;

import java.util.List;
import java.util.Optional;

public interface QuantifiableAbstractService<T extends Quantifiable, ID>{
    QuantityResponse<T> create(T item);
    QuantityResponse<T> update(T item);
    List<T> getAll();
    Optional<T> getOne(ID id);
}
