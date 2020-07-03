package jyp.cooksite.domain.blog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class blogMenu {

	@Id
	@GeneratedValue
	@Column(name = "blogmenu_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Blogs blog;
	
	private String name;
	
	@OneToMany(mappedBy = "blog",cascade=CascadeType.ALL)
	private List<blogPosts> blogpost = new ArrayList<>();
}
