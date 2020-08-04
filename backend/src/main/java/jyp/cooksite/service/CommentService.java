package jyp.cooksite.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.exception.CustomException;
import jyp.cooksite.api.exception.UserNotFoundException;
import jyp.cooksite.api.request.BoardDto;
import jyp.cooksite.api.request.CreateCommentDto;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.dto.CommentListResponse;

import jyp.cooksite.api.service.ResponseService;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.commonboard.boardComments;
import jyp.cooksite.domain.user.User;
import jyp.cooksite.repository.BoardRepository;
import jyp.cooksite.repository.CommentRepository;
import jyp.cooksite.repository.PostRepository;
import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final ResponseService responseService;
	private final UserRepository userRepository;
	@Transactional //addComment(eid,id,CreateCommentDto)
	public Long addComment(String eid ,Long id,CreateCommentDto commentDto) { //id : post_id
		
		Post post= postRepository.findById(id).orElseThrow(CustomException::new);
		

		User user = userRepository.findByEmail(eid).orElseThrow(UserNotFoundException::new);
		
		boardComments bcomments= boardComments.createboardComments(user, post, commentDto.getContents());
		
		commentRepository.save(bcomments);

		return bcomments.getId() ;
	}
	
	//댓글 리스트 조회
	public List<CommentListResponse> getCommentList(Long id){
		Post post = postRepository.findById(id).orElseThrow(CustomException::new); // post 못찾을 경우 예외처리 	
		List<boardComments> bcomments = commentRepository.findByPost1(post);
		
		List<CommentListResponse> result= bcomments.stream()
				.map(b->new CommentListResponse(b))
				.collect(Collectors.toList());
		
		return result;
	}
}
