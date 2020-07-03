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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class boardComments {

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private Board board;
	
	
	
	private String content;
	
	private LocalDate createdDate;
	
	private LocalDate modDate;
	
	
	
	
	
}
