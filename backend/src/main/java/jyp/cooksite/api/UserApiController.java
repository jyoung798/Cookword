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
	//회원가입 api
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
	
	//로그인 api 
	@PostMapping("/user/login")
	//@CrossOrigin(origins = "http://localhost:8080") // logindto => email, pw 
	public LoginUserResponse login(@RequestBody LoginDto logindto) {
		
		User user = userRepository.findByEmail(logindto.getEmail());
		
	
		if(!passwordEncoder.matches(logindto.getPw(), user.getPw())) {
			throw new CEmailSigninFailedException("아이디 및 비밀번호 오류");
		}
	
		return new LoginUserResponse(0, "login", logindto.getEmail(),jwtTokenProvider.createToken(user.getEmail(), user.getRoles()),user.getNickname());
		//return responseService.getSingleResult(jwtTokenProvider.createToken(user.getEmail(), user.getRoles())) ;
	}
	

	//회원 전체 조회 
	@GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userService.findUsers());
    }
	
	
	

	

}
