package jyp.cooksite.domain.blog;

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
public class blogComments {

	@Id @GeneratedValue
	@Column(name = "blogcomment_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blogpost_id")
	private blogPosts blogpost;
	
	private LocalDate createdDate;
	
	private LocalDate modDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	private String content;
	
}
