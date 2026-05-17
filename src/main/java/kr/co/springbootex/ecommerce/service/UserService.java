package kr.co.springbootex.ecommerce.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.springbootex.ecommerce.dto.request.user.LoginRequest;
import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.dto.response.user.LoginResponse;
import kr.co.springbootex.ecommerce.dto.response.user.UserResponse;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.repository.UserRepository;

@Service
public class UserService extends NameAbleCommonService<User, String> {
	
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
    	super(userRepository);
    	this.userRepository = userRepository;
	}
	
	@Override
	protected CommonInfoResponse<String, User> convertToResponse(User user) {
		return UserResponse.from(user);
	}
	
	public LoginResponse login(LoginRequest request,
			HttpServletRequest servletRequest) {
		String id = request.id();
		String password = request.password();
		User user = userRepository.findByIdAndPassword(id, password);
		if(user == null)throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
		HttpSession session = servletRequest.getSession(true);
		session.setAttribute("LOGIN_USER", user.getId());
		return new LoginResponse(user.getName(), 
					user.getUserClassification().name(),
					"로그인 성공");
		
	}
	
	public void logout(HttpServletRequest servletRequest) {
		HttpSession session = servletRequest.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
	}
	
	
	
	
	
	
	
	
	 
	
	




}
