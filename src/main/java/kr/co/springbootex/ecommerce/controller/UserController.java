package kr.co.springbootex.ecommerce.controller;

import kr.co.springbootex.ecommerce.dto.UserDTO;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable){
        List<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id")Long id){
        User user = userService.findById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        User user = userService.createUser(userDTO);
        if(user != null){
            return ResponseEntity.status(201).body("User created");
        }else{
            return ResponseEntity.badRequest().body("Fail Created");
        }
    }

    @PutMapping()
    public  ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO){
        User user = userService.updateUser(userDTO);
        if(user != null){
            return ResponseEntity.ok("User updated");
        }else{
            return ResponseEntity.badRequest().body("Fail Updated");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제할 데이터가 없습니다.");
        }
    }








}
