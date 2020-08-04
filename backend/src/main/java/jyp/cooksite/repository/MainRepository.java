package jyp.cooksite.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;



@Repository
public interface MainRepository extends JpaRepository<Post, Long> {

//	@Query("select p from Post p join fetch p.user u ORDER BY p.id DESC LIMIT 3")
//	List<Post> findAllWithUser(Pageable pageable);
	
	
	//List<Post> findTopByOrderByIdDesc();
	
	List<Post> findByBoard(Board board,org.springframework.data.domain.Pageable pageRequest); //Byname : name 으로 검색한다.
}
