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

	@Transactional // ��ũ�� �ϱ�
	public void addScrap(String email, Long post_id) {

		User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
		Post post = postRepository.findById(post_id).orElseThrow(CustomException::new);
		;

		UserScrapPost scrap = UserScrapPost.createScrap(post);
		user.addScrap(scrap);

		userRepository.save(user);

	}

	// ��ũ�� ��ȸ�ϱ�
	public List<Post> scrapList(String email) {

		User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

		List<UserScrapPost> scraps = user.getScraps(); // ���⼭ ���� 1�� lazy �ε� �ʱ�ȭ 

		List<Post> posts = new ArrayList<Post>();

		for (int i = 0; i < scraps.size(); i++) {
			
			Post post = postRepository.findById(scraps.get(i).getPosts().getId()).orElseThrow(CustomException::new);
			//a����ڰ� �ø����� a�� ��ũ���ߴٸ� a �� �α����� ��� post��ƼƼ�� �����ö� a�� ���Ӽ����ؽ�Ʈ�� �����Ƿ� lazy �ʱ�ȭ �� 
			
			//2. Post post = scraps.get(i).getPosts(); //2. userScrapPost ���� post,user lazy �̹Ƿ� ���⼭ post�� ���Ͻ� ��ü�� ���Ե�
			posts.add(post);
		}

		return posts;

	}
}
