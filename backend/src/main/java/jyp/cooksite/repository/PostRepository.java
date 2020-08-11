package jyp.cooksite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	//@Query("select p from Post p join fetch p.user u")
	@Query(value = "select p from Post p join fetch p.user u where p.board= ?1"
			,countQuery="select count(p) from Post p where p.board = ?1")
	Page<Post> findByBoard(Board board,Pageable pageable); //Byname : name 으로 검색한다.
	
	
}
