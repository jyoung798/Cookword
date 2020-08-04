package jyp.cooksite.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jyp.cooksite.api.controller.UserApiController;
import lombok.Data;

@Data
@ApiModel(value = "join", description = "회원 가입을 할때 필요한 데이터")
public class CreateUserDto {

	@ApiModelProperty(value = "이메일", name = "email", dataType = "String", required = true, example = "abc@abc.com")
	private String email;
	@ApiModelProperty(value = "패스워드", name = "pw", required = true, dataType = "String", example = "123")
	private String pw;
	@ApiModelProperty(required = true,value = "nickname",example = "진영01")
	private String nickname;

}