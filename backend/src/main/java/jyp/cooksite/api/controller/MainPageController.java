package jyp.cooksite.api.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.dto.PostListResponse;
import jyp.cooksite.api.service.ResponseService;
import jyp.cooksite.config.JwtTokenProvider;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.repository.MainRepository;
import jyp.cooksite.repository.UserRepository;
import jyp.cooksite.service.MainService;
import jyp.cooksite.service.UserService;
import lombok.RequiredArgsConstructor;

@Api(tags= {"메인 페이지"})
@RestController
@RequiredArgsConstructor
public class MainPageController {

	private final MainService mainService;
	
	private final ResponseService responseService;
	
	@GetMapping("/cooksite")
	public ListResult< List<PostListResponse> > fetchPosts() {
		
		
			
		 return responseService.getListResult(mainService.getMainList()) ; //list<PostListResponse> PostListResponse=> {title,user.nickname}
		 //responseService.getListResult(boardService.findAll()); //list.안에 post 객체 
	}
	
}
