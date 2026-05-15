package kr.co.springbootex.ecommerce.service;

import kr.co.springbootex.ecommerce.dto.UserDTO;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.mapper.UserMapper;
import kr.co.springbootex.ecommerce.repository.GenericRepository;
import kr.co.springbootex.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends CommonService<User, String> {

    public UserService(GenericRepository<User, String> genericRepository) {
        super(genericRepository);
    }

    public


}
