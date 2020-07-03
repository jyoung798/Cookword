package jyp.cooksite.api.user;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jyp.cooksite.domain.user.User;
import jyp.cooksite.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;

	@PostMapping("/user/join")
	//@CrossOrigin(origins = "http://localhost:8080")
	public CreateUserResponse join(@RequestBody @Valid CreateUserRequest request
			) {
		//System.out.println(request.get("name"));
		User user = new User(request.getEmail(), request.getPw(),request.getNickname());

		Long id = userService.join(user);
		return new CreateUserResponse(user.getId());
	}

	@Data
	static class CreateUserRequest {
		
		private String email;
		private String pw;
		private String nickname;
	}

	@Data
	class CreateUserResponse {
		private Long id;
	
		public CreateUserResponse(Long id) {
			this.id = id;
		}
	}

}
