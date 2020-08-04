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

	// �Խ��� �̸����� �Խ����� ��ȸ. ������� ���� ó��
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
	
	 // �Խ��� �̸����� �Խù� ����Ʈ ��ȸ.
    public List<Post> findPosts(String boardName) {
        return postRepository.findByBoard(findBoard(boardName));
    }
    
    //��ü �Խñ� ����Ʈ 
    public List<Post> findAll(){
    	return postRepository.findAll();
    }
    
    //Ư�� �Խñ� �� ������ 
    public PostDetailResponse findOne(Long id) {
    Post post=postRepository.findById(id).orElseThrow(CustomException::new); // post ã���� PostDetailResponse �� ���Ǵ� board user �� join fetch�� �����;��� 
    	
    PostDetailResponse result= new PostDetailResponse(post);
    	
    	return result; //Post ��ã�� ��� ���� ó��
    }

    //Ư�� �Խñ� ����
    @Transactional
    public void delete(Long id) {
    	Post post = postRepository.findById(id).orElseThrow(CustomException::new);
    	postRepository.delete(post);
    	
    	
    }
    
    //Ư�� �Խù� ����
    @Transactional
    public void update(Long id,BoardDto boarddto) {
    	Post post = postRepository.findById(id).get();
    	//���⼭ �Խñ��� �ڱ� ������ �������� ���� �ʿ� 
    	post.setUpdate(boarddto.getTitle(), boarddto.getContents());
    	
    }
}
