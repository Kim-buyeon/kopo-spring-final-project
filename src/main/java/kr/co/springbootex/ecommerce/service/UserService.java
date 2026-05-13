package kr.co.springbootex.ecommerce.service;

import kr.co.springbootex.ecommerce.dto.UserDTO;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.mapper.UserMapper;
import kr.co.springbootex.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    //private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(Pageable pageable){
        Page<User> page = userRepository.findAll(pageable);
        return page.getContent();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않습니다."));
    }

    public User createUser(UserDTO userDTO){
        User user = new User(userDTO.getName(), userDTO.getAge(), userDTO.getEmail());
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(UserDTO userDTO){
        User user = new User(userDTO.getName(), userDTO.getAge(), userDTO.getEmail());
        return userRepository.save(user);
    }


}
