package kr.co.springbootex.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.co.springbootex.ecommerce.dto.request.NameRequest;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import kr.co.springbootex.ecommerce.entity.base.Nameable;

public interface NameAbleAbstractController<
	T extends Nameable<ID>, ID,
	CreateReq extends NameRequest,
	UpdateReq extends NameRequest,
	Resp>{
	
	ApiResult<Resp> getById(ID id);
	ApiResult<List<Resp>> getAll(Pageable pageable);
	ApiResult<Resp> create(CreateReq request);
	ApiResult<Resp> update(ID id, UpdateReq request);
	ApiResult<Void> delete(ID id);
	
}
