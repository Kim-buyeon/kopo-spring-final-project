package kr.co.springbootex.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import kr.co.springbootex.ecommerce.dto.request.NameRequest;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import kr.co.springbootex.ecommerce.service.NameAbleCommonService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class NameAbleCommonController<
	T extends Nameable<ID>,
	ID,
	CreateReq extends NameRequest,
	UpdateReq extends NameRequest,
	Resp
> implements NameAbleAbstractController<T, ID, CreateReq, UpdateReq, Resp> {

	protected final NameAbleCommonService<T, ID> commonService;
	
	// 엔티티를 프론트엔드용 최종 응답 DTO(Resp)로 바꾸는 역할을 자식에게 위임
	protected abstract Resp convertToResponse(T entity);
	
	// [등록] 등록 요청 DTO(CreateReq)를 새로운 엔티티(T)로 만드는 역할을 자식에게 위임.
	protected abstract T convertToEntity(CreateReq request);
	
	// [수정] 프론트엔드가 보낸 수정 데이터(UpdateReq)를 DB에서 읽어온 기존 엔티티(T)에 덮어쓰는 역할을 자식에게 위임.
    protected abstract void updateEntityFromDto(UpdateReq request, T entity);
	
	
	@Override
	public ApiResult<Resp> getById(ID id) {
		T entity = commonService.getOneOrThrow(id);
		return ApiResult.success(convertToResponse(entity));
	}

	@Override
    @GetMapping
    public ApiResult<List<Resp>> getAll(Pageable pageable) {
        List<T> entities = commonService.getAll(pageable);
        
        List<Resp> list = entities.stream()
                .map(this::convertToResponse)
                .toList();
                
        return ApiResult.success(list);
    }

	@Override
	@PostMapping
	public ApiResult<Resp> create(@RequestBody @Valid CreateReq request) {
		T entity = convertToEntity(request);
		var commonResponse = commonService.create(entity);
		T savedEntiy = commonService.getOneOrThrow(commonResponse.getId());
		return ApiResult.success(convertToResponse(savedEntiy));
	}

	@Override
	@PutMapping("/{id}")
	public ApiResult<Resp> update(@PathVariable("id") ID id, @RequestBody @Valid UpdateReq request) {
		T existing = commonService.getOneOrThrow(id);
		updateEntityFromDto(request, existing);
		var commonResponse = commonService.update(existing);
		T updatedEntity = commonService.getOneOrThrow(commonResponse.getId());
		return ApiResult.success(convertToResponse(updatedEntity));
	}

	@Override
	@DeleteMapping("/{id}")
	public ApiResult<Void> delete(@PathVariable("id") ID id) {
		commonService.remove(id);
		return ApiResult.success(null);
	}
	
	
}
