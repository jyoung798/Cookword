package jyp.cooksite.api.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jyp.cooksite.api.exception.UserNotFoundException;
import jyp.cooksite.api.request.CreateUserDto;
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

@Api(tags= {"회원"})
@RestController
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	//회원가입 api
	@ApiOperation(value="회원 가입",notes = "회원 가입을 진행한다." )
	@ApiResponses({
		@ApiResponse(code=200,message="Success",response=CreateUserResponse.class ),
		@ApiResponse(code=404,message="Not found" )
	})
	@PostMapping("/user/join")
	public CreateUserResponse join(
			@ApiParam(required = true, name="join",value = "회원 정보")
			@RequestBody @Valid CreateUserDto request
			) {
		
		User user = User.builder()
				.nickname(request.getNickname())
				.email(request.getEmail())
				.pw(passwordEncoder.encode(request.getPw()))
				.roles(Collections.singletonList("ROLE_USER"))
				.build();

		Long id = userService.join(user);
	
		return new CreateUserResponse(id);
	}
	
	//로그인 api 
	@PostMapping("/user/login")
	//@CrossOrigin(origins = "http://localhost:8080") // logindto => email, pw 
	public SingleResult<LoginUserResponse> login(@RequestBody LoginDto logindto) throws Exception{
		
		 User user = userRepository.findByEmail(logindto.getEmail()).orElseThrow(UserNotFoundException::new) ;
		 
		
	
		if(!passwordEncoder.matches(logindto.getPw(), user.getPw() ) ) {
			throw new CEmailSigninFailedException("비밀번호가 맞지 않습니다.");
		}
	
		
		return responseService.getSingleResult(new LoginUserResponse(logindto.getEmail(), jwtTokenProvider.createToken(user.getEmail(), user.getRoles()),
				user.getNickname()) );
	}
	

	//회원 전체 조회 
	@GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userService.findUsers());
    }
	
	
	

	

}
