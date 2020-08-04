package jyp.cooksite.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.print.attribute.standard.PageRanges;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.exception.CustomException;
import jyp.cooksite.api.response.dto.PostListResponse;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.repository.BoardRepository;
import jyp.cooksite.repository.MainRepository;
import jyp.cooksite.repository.PostRepository;
import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainService {

	private final MainRepository mainRepository;
	private final BoardRepository boardRepository;
	private final PostRepository postRepository;
	
	public List<List<PostListResponse>>  getMainList() {
		
		//List<Post> posts=mainRepository.findTopByOrderByIdDesc();
		//List<Post> posts=mainRepository.findAll(PageRequest.of(0, 6,Sort.by(Sort.Direction.DESC,"id"))).getContent();
		List<Board> boards = boardRepository.findAll();
		List<List<PostListResponse>> list = new ArrayList<List<PostListResponse>>();
		for (Board board : boards) {
			List<Post> posts=mainRepository.findByBoard(
					board, PageRequest.of(0, 6,Sort.by(Sort.Direction.DESC,"id"))
					);
			List<PostListResponse> r = posts.stream()
					.map(p -> new PostListResponse(p))
					.collect(Collectors.toList());
			list.add(r);
		}
		
		
		return list;
	}
	
	public Board findBoard(String boardName) {
        return Optional.ofNullable(boardRepository.findByTitle(boardName)).orElseThrow(CustomException::new);
    }
	
	 // 게시판 이름으로 게시물 리스트 조회.
//    public List<Post> findPosts(String boardName) {
//        return postRepository.findByBoard(findBoard(boardName));
//    }
}
