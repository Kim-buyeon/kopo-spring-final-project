package kr.co.springbootex.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import kr.co.springbootex.ecommerce.dto.request.ListHolder;
import kr.co.springbootex.ecommerce.dto.request.UpdateHolder;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import kr.co.springbootex.ecommerce.service.OrderFlowCommonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class OrderFlowCommonController<
	T,
	ID,
	CreateReq extends ListHolder<?>,
	UpdateReq extends UpdateHolder<ID, ?>,
	Resp
> implements OrderFlowAbstractController<T, ID, CreateReq, UpdateReq, Resp> {

	private final OrderFlowCommonService<T, ID> commonSerice;
	
	protected abstract Resp convertToResponse(T entity);
	protected abstract List<T> convertToEntity(CreateReq request);
	protected abstract void updateEntityFromDto(UpdateReq request);
	
	@Override
	@GetMapping("/{id}")
	public ApiResult<Resp> getById(@PathVariable("id") ID id) {
		T entity = commonSerice.getOneOrThrow(id);
		return ApiResult.success(convertToResponse(entity));
	}

	@Override
	@GetMapping
	public ApiResult<List<Resp>> getAll(Pageable pageable) {
		List<T> entites = commonSerice.getAll(pageable);
		List<Resp> list = entites.stream()
							.map(this::convertToResponse)
							.toList();
		return ApiResult.success(list);
	}

	@Override
	@PostMapping
	public ApiResult<Resp> create(CreateReq request) {
		List<T> entities = convertToEntity(request);
		
		if (entities.isEmpty()) {
			throw new IllegalArgumentException("저장할 항목이 존재하지 않습니다.");
		}
		
		T firstEntity = entities.get(0);
		commonSerice.create(firstEntity); 
		return ApiResult.success(convertToResponse(firstEntity));
	}

	@Override
	@PutMapping("/{id}")
	public ApiResult<Resp> update(@PathVariable("id")ID id, UpdateReq request) {
		T entity = commonSerice.getOneOrThrow(id);
		updateEntityFromDto(request);
		commonSerice.update(entity);
		return ApiResult.success(convertToResponse(entity));
	}

	@Override
	public ApiResult<Void> delete(ID id) {
		commonSerice.getOneOrThrow(id);
		commonSerice.remove(id);
		return ApiResult.success(null);
	}
}