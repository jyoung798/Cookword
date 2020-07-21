package jyp.cooksite.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.user.User;

@Repository
public class UserRepository  {

	@PersistenceContext
	private EntityManager em;

	public void save(User user) {
		em.persist(user);
	}

	public User findOne(Long id) {
		
		return em.find(User.class, id);
	}

	
	//모든 사용자 유저를 검색하고 결과를 List 로 반환합니다.
	public List<User> findAll() {
		return em.createQuery("select u from User u", User.class).getResultList();
	}

	//사용자 이름을 통해서 검색하고 결과를 List 로 반환합니다.
	public List<User> findByName(String name) {
		return em.createQuery("select u from User u where u.name = :name", User.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	//email로 검색
	public User findByEmail(String email) {
		User user;
		try {
			 user = em.createQuery("select u from User u where u.email = :email", User.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		return user;
		
	
	} 
	
}
