package jyp.cooksite.repository;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.domain.commonboard.boardComments;
import lombok.RequiredArgsConstructor;

@Repository
public interface CommentRepository extends JpaRepository<boardComments, Long> {

	
//	@Query("select c from boardComments c join fetch c.user u join fetch c.post p")
//	public List<boardComments> findAllWithUserAndBoard();
	
	@Query("select c from boardComments c join fetch c.user u where c.post= ?1")
	List<boardComments> findByPost1(Post post);
	
	List<boardComments> findByPost(Post post);
	
	
}
