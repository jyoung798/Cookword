package jyp.cooksite.domain.commonboard;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jyp.cooksite.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter  //getter 없으면 get해서 가져올때 못가져옴 
public class Post extends commonDate {

	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;
	
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Column(length = 2000)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
			
	@OneToMany(mappedBy = "post",cascade =CascadeType.REMOVE )
	private List<boardComments> boardcomments = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Long getId() {
		return id;
	}
	public Post() {}
	
    public Post(User user, Board board, String title, String content) {
        this.user = user;
        this.board = board;
        this.title = title;
        this.content = content;
    }

   
    public Post setUpdate( String title, String content) {
       
        this.title = title;
        this.content = content;
        return this;
    }
}
