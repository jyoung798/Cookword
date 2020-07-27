package jyp.cooksite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.commonboard.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByBoard(Board board);
}
