package kr.co.springbootex.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.co.springbootex.ecommerce.dto.request.user.LoginRequest;
import kr.co.springbootex.ecommerce.dto.request.user.UserCreateRequest;
import kr.co.springbootex.ecommerce.dto.request.user.UserUpdateRequest;
import kr.co.springbootex.ecommerce.dto.response.ApiResult;
import kr.co.springbootex.ecommerce.dto.response.user.LoginResponse;
import kr.co.springbootex.ecommerce.dto.response.user.UserResponse;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController extends NameAbleCommonController<
	User, 
	String, 
	UserCreateRequest,
	UserUpdateRequest,
	UserResponse> {

	private final UserService userService;
	public UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}

	@Override
	protected UserResponse convertToResponse(User user) {
		return UserResponse.from(user);
	}

	@Override
	protected User convertToEntity(UserCreateRequest request) {
		return User.builder()
				.id(request.userId())
				.name(request.name())
				.email(request.email())
				.password(request.password())
				.telNo(request.telNo())
				.userClassification(request.userClassification())
				.userStatus(request.userStatus())
				.build();
	}

	@Override
	protected void updateEntityFromDto(UserUpdateRequest request, User user) {
		user.setName(request.name());
		user.setEmail(request.email());
		user.setPassword(request.password());
		user.setTelNo(request.telNo());
		
	}
	
	@PostMapping("/login")
	public ApiResult<LoginResponse> login(
			@RequestBody @Valid LoginRequest request,
			HttpServletRequest servletRequest){
		LoginResponse loginResponse = userService.login(request, servletRequest);
		return ApiResult.success(loginResponse);
		
	}
	
	@PostMapping("/logout")
	public ApiResult<Void> logout(HttpServletRequest servletRequest){
		userService.logout(servletRequest);
		return ApiResult.success(null);
	}

}
