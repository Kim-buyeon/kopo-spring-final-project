package kr.co.springbootex.ecommerce.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.springbootex.ecommerce.dto.response.order.DailyOrderStatus;
import kr.co.springbootex.ecommerce.dto.response.order.UserOrderStats;

@Mapper
public interface OrderMapper {
	List<DailyOrderStatus> countAndGroupByDaily();
	List<UserOrderStats> countAndGroupByUserId(String userId);
}
