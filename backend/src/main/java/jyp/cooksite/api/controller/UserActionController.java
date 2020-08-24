package jyp.cooksite.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import jyp.cooksite.api.request.BoardDto;
import jyp.cooksite.api.response.CommonResult;
import jyp.cooksite.api.response.SingleResult;
import jyp.cooksite.api.response.dto.PostListResponse;
import jyp.cooksite.api.service.ResponseService;
import jyp.cooksite.config.JwtTokenProvider;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.repository.UserRepository;
import jyp.cooksite.service.UserActionService;
import jyp.cooksite.service.UserService;
import jyp.cooksite.service.query.QueryPostListService;
import lombok.RequiredArgsConstructor;

@Api(tags= {"스크랩,좋아요 기능"})
@RestController
@RequiredArgsConstructor
public class UserActionController {

	private final UserActionService userActionService;
	private final ResponseService responseService;
	private final QueryPostListService queryPostList;
	
	@PostMapping("/posts/scrap/{id}") //스크랩 하기 
	public CommonResult post(@PathVariable("id") Long postId) {
		System.out.println("post_id -----"+postId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String eid = authentication.getName();
			
        userActionService.addScrap(eid, postId);
			
        return responseService.getSuccessResult();
	}
	
	@DeleteMapping("/posts/cancelscrap")
	public CommonResult cancelScrap() {
		return null;
	}
	
	@GetMapping("/Mypage/scrap") //마이페이지 스크랩 목록 불러오기 
	public List<PostListResponse> scrapList(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String eid = authentication.getName();
        
       
        
        return  queryPostList.showScrapList(eid);
	}
	
	
}
