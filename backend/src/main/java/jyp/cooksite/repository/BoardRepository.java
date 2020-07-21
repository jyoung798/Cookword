package jyp.cooksite.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.user.User;


@Repository
public class BoardRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(Board board) {
		em.persist(board);
	}

	public Board findOne(Long id) {
	
		return em.find(Board.class, id);
	}
	
	public void delete(Board board) {
		
		
		em.remove(board);
	}
	
	
	
	//모든 사용자 유저를 검색하고 결과를 List 로 반환합니다.
	public List<Board> findAll() {
		return em.createQuery("select b from Board b", Board.class).getResultList();
	}

	//게시판 제목을 통해 검색 
	public List<Board> findByName(String name) {
		return em.createQuery("select b from Board b where b.name = :name", Board.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	//게시판 메뉴를 통해 검색
	public List<Board> findByMenu(String menu) {
		return em.createQuery("select b from Board b where b.menu = :menu", Board.class)
				.setParameter("menu", menu)
				.getResultList();
	}
	
	
}
