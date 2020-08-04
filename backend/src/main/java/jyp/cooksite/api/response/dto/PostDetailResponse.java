package jyp.cooksite.api.response.dto;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.user.User;
import lombok.Data;

@Data
public class PostDetailResponse {

	private Long id;
	private String title;
	private String content;
	private Long board_id;
	private String username;
	
	public PostDetailResponse(Post post) {
		this.id= post.getId();
		this.title=post.getTitle();
		this.content= post.getContent();
		this.board_id= post.getBoard().getId();  // Board to one 
		this.username= post.getUser().getNickname(); // user to one 
		
	}
	
}
