package jyp.cooksite.domain.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import jyp.cooksite.domain.Address;
import jyp.cooksite.domain.blog.Blogs;
import jyp.cooksite.domain.blog.blogComments;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.boardComments;
import lombok.Builder.Default;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	
	@NotEmpty
	private String nickname;

	@NotEmpty
	private String pw;

	@NotEmpty
	private String email;

	@Embedded
	private Address address;

	// ���� �Խ���
	@OneToMany(mappedBy = "user")
	private List<Board> boards = new ArrayList<>();

	//���� �Խ��� ��� 
	@OneToMany(mappedBy = "user")
	private List<boardComments> boardcomments = new ArrayList<>();
	
	// ���� ����
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_relations", 
				joinColumns = @JoinColumn(name = "owner_id"), 
				inverseJoinColumns = @JoinColumn(name = "follower_id"))
	private Set<User> followers = new HashSet<User>();

	public void addFollower(User follower) {// ���� ��õ�� User�� addFollower �޼ҵ忡 �� �ڽ��� User �� ��
		followers.add(follower);
		follower.following.add(this);
	}

	@ManyToMany(mappedBy = "followers")
	private Set<User> following = new HashSet<User>();;

	public void addFollowing(User followed) { // ���� ��õ�� User �� ��
		followed.addFollower(this); // ���� ��õ�� user�� �ȷο�� ���� �޼ҵ� ȣ��
	}

	protected User() {

	}

	//���� ������ 
	public User(String email, String pw, String nickname) {
		
		this.email = email;
		this.pw=pw;
		this.nickname = nickname;
	}

	
	//���α� ����
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
	private Blogs blog;
	
	//���� ���α� comment ����
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<blogComments> blogcomments = new ArrayList<>();

}