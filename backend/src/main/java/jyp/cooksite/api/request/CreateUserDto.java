package jyp.cooksite.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jyp.cooksite.api.controller.UserApiController;
import lombok.Data;

@Data
@ApiModel(value = "join", description = "ȸ�� ������ �Ҷ� �ʿ��� ������")
public class CreateUserDto {

	@ApiModelProperty(value = "�̸���", name = "email", dataType = "String", required = true, example = "abc@abc.com")
	private String email;
	@ApiModelProperty(value = "�н�����", name = "pw", required = true, dataType = "String", example = "123")
	private String pw;
	@ApiModelProperty(required = true,value = "nickname",example = "����01")
	private String nickname;

}