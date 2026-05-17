package kr.co.springbootex.ecommerce.dto.response.user;

import kr.co.springbootex.ecommerce.dto.response.CommonInfoResponse;
import kr.co.springbootex.ecommerce.entity.User;
import kr.co.springbootex.ecommerce.entity.constant.UserClassification;
import kr.co.springbootex.ecommerce.entity.constant.UserStatus;
import lombok.Getter;

@Getter
public class UserResponse extends CommonInfoResponse<String, User>{

    private final String email;
    private final String telNo;
    private final UserClassification userClassification;
    private final UserStatus userStatus;
    
    public UserResponse(String id,
                        String name,
                        String email,
                        String telNo,
                        UserClassification userClassification,
                        UserStatus userStatus) {
        super(id, name);
        this.email = email;
        this.telNo = telNo;
        this.userClassification = userClassification;
        this.userStatus = userStatus;
    }
    
    //정적 팩토리 메서드
    public static UserResponse from(User user) {
    	return new UserResponse(user.getId(), 
    			user.getName(), 
    			user.getEmail(),
    			user.getTelNo(),
    			user.getUserClassification(), 
    			user.getUserStatus());
    }
}
