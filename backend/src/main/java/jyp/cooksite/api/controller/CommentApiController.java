package jyp.cooksite.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jyp.cooksite.api.request.BoardDto;
import jyp.cooksite.api.request.CreateCommentDto;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.SingleResult;
import jyp.cooksite.api.response.dto.CommentListResponse;
import jyp.cooksite.api.service.ResponseService;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.commonboard.boardComments;
import jyp.cooksite.service.BoardService;
import jyp.cooksite.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

	private final ResponseService responseService;
	private final BoardService boardService;
	private final CommentService commentservice;
	
	//게시판 댓글 추가 요청 
	@PostMapping("/posts/detail/addcomment/{id}")  //post_id
	public SingleResult<Long> post(@PathVariable("id") Long id,@RequestBody CreateCommentDto CommentDto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String eid = authentication.getName();
        
        
//		boardComments bc= boardComments.createboardComments(user, post, contents)		
			
        return responseService.getSingleResult(
        		commentservice.addComment(eid,id,CommentDto) );
	}
	
	//게시판 댓글 리스트 
			@GetMapping("/posts/detail/comments/{id}")//post_id
			public ListResult<CommentListResponse> fetchDetail(@PathVariable("id") Long id) {
				
			
				 return responseService.getListResult(commentservice.getCommentList(id));//commentservice.getCommentList(id);  //list.안에 Board 객체 
			} 
}
