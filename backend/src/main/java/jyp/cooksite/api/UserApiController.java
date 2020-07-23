package jyp.cooksite.api;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jyp.cooksite.api.request.CreateUserRequest;
import jyp.cooksite.api.request.LoginDto;
import jyp.cooksite.api.response.CreateUserResponse;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.LoginUserResponse;
import jyp.cooksite.api.response.SingleResult;
import jyp.cooksite.api.service.ResponseService;
import jyp.cooksite.config.JwtTokenProvider;
import jyp.cooksite.domain.user.User;
import jyp.cooksite.exception.CEmailSigninFailedException;
import jyp.cooksite.repository.UserRepository;
import jyp.cooksite.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	//ȸ������ api
	@PostMapping("/user/join")
	//@CrossOrigin(origins = "http://localhost:8080")
	public CreateUserResponse join(@RequestBody @Valid CreateUserRequest request
			) {
		
		User user = User.builder()
				.nickname(request.getNickname())
				.email(request.getEmail())
				.pw(passwordEncoder.encode(request.getPw()))
				.roles(Collections.singletonList("ROLE_USER"))
				.build();

		Long id = userService.join(user);
	
		return new CreateUserResponse(id,user.getNickname());
	}
	
	//�α��� api 
	@PostMapping("/user/login")
	//@CrossOrigin(origins = "http://localhost:8080") // logindto => email, pw 
	public LoginUserResponse login(@RequestBody LoginDto logindto) {
		
		User user = userRepository.findByEmail(logindto.getEmail());
		
	
		if(!passwordEncoder.matches(logindto.getPw(), user.getPw())) {
			throw new CEmailSigninFailedException("���̵� �� ��й�ȣ ����");
		}
	
		return new LoginUserResponse(0, "login", logindto.getEmail(),jwtTokenProvider.createToken(user.getEmail(), user.getRoles()),user.getNickname());
		//return responseService.getSingleResult(jwtTokenProvider.createToken(user.getEmail(), user.getRoles())) ;
	}
	

	//ȸ�� ��ü ��ȸ 
	@GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // ��������Ͱ� �������ΰ�� getListResult�� �̿��ؼ� ����� ����Ѵ�.
        return responseService.getListResult(userService.findUsers());
    }
	
	
	

	

}
