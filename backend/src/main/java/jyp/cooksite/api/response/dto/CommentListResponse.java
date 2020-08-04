package jyp.cooksite.api.response.dto;

import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.commonboard.boardComments;
import jyp.cooksite.domain.user.User;
import lombok.Data;

@Data
public class CommentListResponse {

	private Long id;
	private String username;
	private String content;
	
	public CommentListResponse(boardComments bC) {
		this.id=bC.getId();
		this.username=bC.getUser().getNickname(); // user to one 
		this.content=bC.getContent();
	}
}
