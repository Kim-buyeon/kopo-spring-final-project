package kr.co.springbootex.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kr.co.springbootex.ecommerce.dto.response.IdResult;

public interface OrderFlowAbstractService<T, ID> {

	IdResult<ID> create(T item);
	IdResult<ID> update(T item);
	void remove(ID id);
	Optional<T> getOne(ID id);
	List<T> getAll(Pageable pageable);
	List<T> getAll();
}
