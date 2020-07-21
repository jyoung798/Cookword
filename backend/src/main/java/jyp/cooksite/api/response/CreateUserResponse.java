package jyp.cooksite.api.response;

import jyp.cooksite.api.UserApiController;
import lombok.Data;

@Data
public class CreateUserResponse {
	private Long id;
	private String nickname;
	public CreateUserResponse(Long id,String nickname) {
		this.id = id;
		this.nickname=nickname;
	}
}