package jyp.cooksite.domain.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jyp.cooksite.domain.Address;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.boardComments;
import lombok.Builder.Default;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jyp.cooksite.domain.commonboard.commonDate;
@Builder
@Entity
@Getter @Setter
@NoArgsConstructor // ���ھ��� �����ڸ� �ڵ� ���� 
@AllArgsConstructor //
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends commonDate implements UserDetails {
	
	
		
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	

	@NotEmpty
	private String nickname;

	@NotEmpty
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String pw;

	
	@Column(nullable = false ,unique = true) //unique -> 2�� ���ÿ� ���� ���� 
	private String email;

	@Embedded
	private Address address;

	// ����
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	
	

	// ���� �Խ��� ���
	@OneToMany(mappedBy = "user")
	private List<boardComments> boardcomments = new ArrayList<>();

	// ���� ����
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_relations", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "follower_id"))
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

	// ��α� ����
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private Blogs blog;

	// ���� ��α� comment ����
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<blogComments> blogcomments = new ArrayList<>();
	
	/*
	 * userdetail ���� �޼ҵ� 
	 */
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.email;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * ��ũ��
	 * 
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserScrapPost> scraps = new ArrayList<>();

	public void addScrap(UserScrapPost scrap) {
		scraps.add(scrap);
		scrap.setUser(this);
	}
}
