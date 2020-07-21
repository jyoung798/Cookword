package jyp.cooksite.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {
	@ApiModelProperty(value = "���� �������� : true/false")
	private boolean success;
	
	@ApiModelProperty(value = "���� �ڵ� ��ȣ : >= 0 ����, < 0 ������")
	private int code;
	
	@ApiModelProperty(value = "���� �޽���")
	private String msg;
}
