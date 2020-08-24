package jyp.cooksite.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.exception.CustomException;
import jyp.cooksite.api.exception.UserNotFoundException;
import jyp.cooksite.api.response.dto.PostListResponse;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.user.User;
import jyp.cooksite.repository.PostRepository;
import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import jyp.cooksite.domain.user.UserScrapPost;;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserActionService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	@Transactional // 스크랩 하기
	public void addScrap(String email, Long post_id) {

		User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
		Post post = postRepository.findById(post_id).orElseThrow(CustomException::new);
		;

		UserScrapPost scrap = UserScrapPost.createScrap(post);
		user.addScrap(scrap);

		userRepository.save(user);

	}

	// 스크랩 조회하기
	public List<Post> scrapList(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

		List<UserScrapPost> scraps = user.getScraps(); // 여기서 쿼리 1번 lazy 로딩 초기화 

		List<Post> posts = new ArrayList<Post>();

		for (int i = 0; i < scraps.size(); i++) {
			
			Post post = postRepository.findById(scraps.get(i).getPosts().getId()).orElseThrow(CustomException::new);
			//a사용자가 올린글을 a가 스크랩했다면 a 로 로그인할 경우 post엔티티를 가져올때 a가 영속성컨텍스트에 있으므로 lazy 초기화 됨 
			
			//2. Post post = scraps.get(i).getPosts(); //2. userScrapPost 에서 post,user lazy 이므로 여기서 post는 프록시 객체가 대입됨
			posts.add(post);
		}

		return posts;

	}
}
