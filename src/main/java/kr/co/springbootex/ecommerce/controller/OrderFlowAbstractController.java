package kr.co.springbootex.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.co.springbootex.ecommerce.dto.request.ListHolder;
import kr.co.springbootex.ecommerce.dto.request.UpdateHolder;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;

public interface OrderFlowAbstractController<
	T,
	ID,
	CreateReq extends ListHolder<?>,
	UpdateReq extends UpdateHolder<ID, ?>,
	Resp>{
	ApiResult<Resp> getById(ID id);
	ApiResult<List<Resp>> getAll(Pageable pageable);
	ApiResult<Resp> create(CreateReq request);
	ApiResult<Resp> update(ID id, UpdateReq request);
	ApiResult<Void> delete(ID id);

}
