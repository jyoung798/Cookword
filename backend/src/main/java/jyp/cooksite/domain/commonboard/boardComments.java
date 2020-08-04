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
@NoArgsConstructor // ���ھ��� �����ڸ� �ڵ� ����
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
		bc.setPost(post); //user �� post set �޼ҵ� ���� ����� �������� �������� : ��Ͽ��� ��� ���� ��ȸ�Ҷ�
		bc.setContent(contents);
		
		return bc;
	}
	
	
	
}
