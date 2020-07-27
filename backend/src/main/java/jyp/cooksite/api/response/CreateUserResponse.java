package jyp.cooksite.api.response;

import jyp.cooksite.api.controller.UserApiController;
import lombok.Data;

@Data
public class CreateUserResponse extends CommonResult {
	private Long id;
	public CreateUserResponse(Long id) {
		this.id = id;
		
	}
	
}