package jyp.cooksite.api.response.dto;

import org.springframework.data.domain.Page;

import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.user.User;
import lombok.Data;

@Data
public class PostListResponse {

	private Long id;
	private String title;
	private String nickname;
	
	public PostListResponse(Post post) {
		this.id = post.getId();
		this.title= post.getTitle();
		this.nickname=post.getUser().getNickname();
	}
	
	
}
