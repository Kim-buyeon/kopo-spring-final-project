package kr.co.springbootex.ecommerce.mapper;

import kr.co.springbootex.ecommerce.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    int insert(UserDTO user);
    int update(UserDTO user);
    int delete(Long id);
}
