package jyp.cooksite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findByBoard(Board board,Pageable pageable); //Byname : name 으로 검색한다.
	
	
}
