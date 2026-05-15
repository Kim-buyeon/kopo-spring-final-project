package kr.co.springbootex.ecommerce.service;

import kr.co.springbootex.ecommerce.repository.GenericRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CommonService<T, ID> implements NameAbleAbstractService<T, ID> {

    protected final GenericRepository<T, ID> genericRepository;

    @Override
    public void create(T item) {
        genericRepository.save(item);
    }

    @Override
    public void update(T item) {
        genericRepository.save(item);
    }

    @Override
    public void remove(ID id) {
        genericRepository.deleteById(id);
    }

    @Override
    public Optional<T> getOne(ID id) {
        return genericRepository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return genericRepository.findAll();
    }
}
