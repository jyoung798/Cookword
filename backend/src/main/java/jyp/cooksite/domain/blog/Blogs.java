package jyp.cooksite.domain.blog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import jyp.cooksite.domain.user.User;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class Blogs {

	@Id
	@GeneratedValue
	@Column(name = "blog_id")
	private Long id;
	
	private LocalDate createdDate;
	
	//user 한명이 여러개 블로그 가질수 있다고 한다면 User에 mapped by 사용하자
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	
	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "blog" )
	private blogSetting blogsetting;
	
	@OneToMany(mappedBy = "blog",cascade=CascadeType.ALL)
	private List<blogMenu> blogmenus= new ArrayList<>();
	
	@OneToMany(mappedBy = "blog",cascade=CascadeType.ALL)
	private List<blogPosts> blogposts = new ArrayList<>();
	
	
}
