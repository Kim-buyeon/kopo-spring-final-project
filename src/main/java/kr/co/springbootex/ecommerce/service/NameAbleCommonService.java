package kr.co.springbootex.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.entity.base.Nameable;
import kr.co.springbootex.ecommerce.repository.NameableRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class NameAbleCommonService<T extends Nameable<ID>, ID> implements NameAbleAbstractService<T, ID> {

	protected final NameableRepository<T, ID> nameableRepository;
	
	//자식 서비스가 구현해야할 변환 추상 메서드
	protected abstract CommonInfoResponse<ID, T> convertToResponse(T entity);
	
	@Override
	public CommonInfoResponse<ID, T> create(T item) {
		T saved = nameableRepository.save(item);
		//저장 후 변환 처리는 자식에게 위임하여 다형성 확보
		return convertToResponse(saved);
	}

	@Override
	public CommonInfoResponse<ID, T> update(T item) {
		T updated = nameableRepository.save(item);
        return convertToResponse(updated);
	}

	@Override
	public void remove(ID id) {
		nameableRepository.deleteById(id);
	}

	@Override
	public Optional<T> getOne(ID id) {
		return nameableRepository.findById(id);
	}

	@Override
	public List<T> getAll(Pageable pageable) {
		Page<T> page = nameableRepository.findAll(pageable);
		return page.getContent();
	}
	
	public T getOneOrThrow(ID id) {
        return this.getOne(id)
                   .orElseThrow(() -> new IllegalArgumentException("해당 데이터를 찾을 수 없습니다. ID: " + id));
    }


}
