package jyp.cooksite.domain.commonboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jyp.cooksite.domain.Address;
import jyp.cooksite.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor // ���ھ��� �����ڸ� �ڵ� ����
@AllArgsConstructor //
public class Board extends commonDate {

	@Id
	@GeneratedValue
	@Column(name = "board_id")
	private Long id;

	@Column(nullable = false,length = 100)
	private String title;
	
	

	

	

}
