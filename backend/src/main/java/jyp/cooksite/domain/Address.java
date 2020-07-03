package jyp.cooksite.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	
	
	public Address(String city, String street, String zipcode) {
		
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}



	protected Address() { //리플렉션 프록시 같은 스킬을 쓰기위해서는 일단 기본 생성자 필요 
		 //jpa 구현 라이브러리가 객체를 생성할 때  리플렉션 같은 기술 사용하려고 
	}
	
	
}
