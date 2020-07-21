package jyp.cooksite.api.response;

import lombok.Data;

@Data
public class LoginUserResponse {

	private int status;
	private String message;
	private String nickname; //nickname
	private String email;
	private String token;

	public LoginUserResponse(int status, String message,String email,String token) {
		super();
		this.status = status;
		this.message = message;
	
		this.email=email;
		this.token=token;
	}

	
}
