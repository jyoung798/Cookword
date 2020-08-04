package jyp.cooksite.api.request;

import lombok.Data;

@Data
public class BoardDto {
	
	private String title; //Board 엔티티의 title
	
	private String menu;
	
	private String contents;
}
