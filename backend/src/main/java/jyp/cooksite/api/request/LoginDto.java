package jyp.cooksite.api.request;

import lombok.Data;

@Data
public class LoginDto {

	private String email;
	private String pw;
}
