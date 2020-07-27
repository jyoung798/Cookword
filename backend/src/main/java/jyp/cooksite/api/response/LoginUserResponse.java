package jyp.cooksite.api.response;

import lombok.Data;

@Data
public class LoginUserResponse {

	
	private String nickname; //nickname
	private String email;
	private String token;

	public LoginUserResponse(String email,String token,String nickname) {
		super();
		
		this.email=email;
		this.token=token;
		this.nickname=nickname;
	}

	
}
