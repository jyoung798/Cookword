package jyp.cooksite.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.user.User;

@Repository
public class UserRepository {

	@PersistenceContext
	private EntityManager em;

	public void save(User user) {
		em.persist(user);
	}

	public User findOne(Long id) {
		return em.find(User.class, id);
	}

	public List<User> findAll() {
		return em.createQuery("select u from User u", User.class).getResultList();
	}

	public List<User> findByName(String name) {
		return em.createQuery("select u from User u where u.name = :name", User.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	//email·Î °Ë»ö
	public List<User> findByEmail(String email) {
		
		return em.createQuery("select u from User u where u.email = :email", User.class)
				.setParameter("email", email)
				.getResultList();
	} 
	
}
