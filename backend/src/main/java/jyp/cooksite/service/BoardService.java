package jyp.cooksite.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.exception.CustomException;
import jyp.cooksite.api.exception.UserNotFoundException;
import jyp.cooksite.api.request.BoardDto;
import jyp.cooksite.api.request.LoginDto;
import jyp.cooksite.api.response.LoginUserResponse;
import jyp.cooksite.api.response.SingleResult;
import jyp.cooksite.api.response.dto.CommentListResponse;
import jyp.cooksite.api.response.dto.PostDetailResponse;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.user.User;
import jyp.cooksite.repository.BoardRepository;
import jyp.cooksite.repository.PostRepository;
import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final PostRepository postRepository;

	// 게시판 이름으로 게시판을 조회. 없을경우 예외 처리
    public Board findBoard(String boardName) {
        return Optional.ofNullable(boardRepository.findByTitle(boardName)).orElseThrow(CustomException::new);
    }	
    
	// Post(User user, Board board, String title, String content)
	@Transactional
	public Long post(String eid ,BoardDto boardDto) {
		Board board = findBoard(boardDto.getMenu());
		User user = userRepository.findByEmail(eid).orElseThrow(UserNotFoundException::new);
		
		Post post = new Post(user,board,boardDto.getTitle(),boardDto.getContents());
		
		postRepository.save(post);
		return (long)post.getId() ;
	}
	
	 // 게시판 이름으로 게시물 리스트 조회.
    public List<Post> findPosts(String boardName) {
        return postRepository.findByBoard(findBoard(boardName));
    }
    
    //전체 게시글 리스트 
    public List<Post> findAll(){
    	return postRepository.findAll();
    }
    
    //특정 게시글 상세 페이지 
    public PostDetailResponse findOne(Long id) {
    Post post=postRepository.findById(id).orElseThrow(CustomException::new); // post 찾을때 PostDetailResponse 에 사용되는 board user 도 join fetch로 가져와야함 
    	
    PostDetailResponse result= new PostDetailResponse(post);
    	
    	return result; //Post 못찾을 경우 에러 처리
    }

    //특정 게시글 삭제
    @Transactional
    public void delete(Long id) {
    	Post post = postRepository.findById(id).orElseThrow(CustomException::new);
    	postRepository.delete(post);
    	
    	
    }
    
    //특정 게시물 수정
    @Transactional
    public void update(Long id,BoardDto boarddto) {
    	Post post = postRepository.findById(id).get();
    	//여기서 게시글이 자기 글인지 권한인증 로직 필요 
    	post.setUpdate(boarddto.getTitle(), boarddto.getContents());
    	
    }
}
