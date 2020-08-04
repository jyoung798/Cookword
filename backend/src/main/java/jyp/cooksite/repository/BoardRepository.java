package jyp.cooksite.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.user.User;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


	Board findByTitle(String title);
	

	
}
