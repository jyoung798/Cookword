package jyp.cooksite.domain.commonboard;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import jyp.cooksite.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor // 인자없는 생성자를 자동 생성 
@AllArgsConstructor //
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;
	
	private String name;
}
