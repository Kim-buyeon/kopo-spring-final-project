package kr.co.springbootex.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import kr.co.springbootex.ecommerce.dto.response.IdResult;
import kr.co.springbootex.ecommerce.repository.GenericRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class OrderFlowCommonService<T, ID> implements OrderFlowAbstractService<T, ID> {

	protected final GenericRepository<T, ID> repository;
	
	//엔티티(T)를 공통 인터페이스인 IdResult<ID>로 변환하는 추상 메서드 선언
    // 자식 서비스(OrderService, BasketService)가 이 메서드를 오버라이드해서 각자의 Response를 리턴하게 됩니다.
    protected abstract IdResult<ID> convertToIdResult(T entity);
	
    @Override
	public IdResult<ID> create(T item) {
		T saved = repository.save(item);
		return convertToIdResult(saved);
	}

	@Override
	public IdResult<ID> update(T item) {
		T updated = repository.save(item);
		return convertToIdResult(updated);
	}

	@Override
	public void remove(ID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<T> getOne(ID id) {
		return repository.findById(id);
	}

	@Override
	public List<T> getAll(Pageable pageable) {
		return repository.findAll(pageable).getContent();
	}
	
	
	
	public T getOneOrThrow(ID id) {
		return this.getOne(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 데이터를 찾을 수 없습니다. ID: " + id));
	}

	@Override
	public List<T> getAll() {
		return repository.findAll();
	}

}
