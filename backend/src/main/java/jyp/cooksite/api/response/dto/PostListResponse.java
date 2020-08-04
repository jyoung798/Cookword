package jyp.cooksite.api.response.dto;

import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.user.User;
import lombok.Data;

@Data
public class PostListResponse {

	private String title;
	private String nickname;
	
	public PostListResponse(Post post) {
		this.title= post.getTitle();
		this.nickname=post.getUser().getNickname();
	}
}
