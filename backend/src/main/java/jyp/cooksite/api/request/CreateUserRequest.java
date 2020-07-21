package jyp.cooksite.api.request;

import jyp.cooksite.api.UserApiController;
import lombok.Data;

@Data
public class CreateUserRequest {
	
	private String email;
	private String pw;
	private String nickname;
	
	
}