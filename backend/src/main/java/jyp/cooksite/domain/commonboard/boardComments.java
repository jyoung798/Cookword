package jyp.cooksite.domain.commonboard;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jyp.cooksite.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor // 인자없는 생성자를 자동 생성
@AllArgsConstructor //
public class boardComments extends commonDate {

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	private String content;
	
	private LocalDate createdDate;
	
	private LocalDate modDate;
	
	//boardComments(user,post,commentDto.getContents())
	public static boardComments createboardComments(User user,Post post , String contents) {
		boardComments bc = new boardComments();
		bc.setUser(user); 
		bc.setPost(post); //user 랑 post set 메소드 만들어서 양방향 연관관계 만들어야함 : 목록에서 댓글 개수 조회할때
		bc.setContent(contents);
		
		return bc;
	}
	
	
	
}
