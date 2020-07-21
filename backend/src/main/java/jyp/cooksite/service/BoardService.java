package jyp.cooksite.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.request.LoginDto;
import jyp.cooksite.api.response.LoginUserResponse;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.user.User;
import jyp.cooksite.repository.BoardRepository;
import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long post(Board board) {
		boardRepository.save(board);

		return (long) board.getId();
	}

	@Transactional
	public void delete(Long id) {
		Board board = boardRepository.findOne(id);

		boardRepository.delete(board);
	}

	@Transactional
	public void update(Long id, String title, String content) {
		Board board = boardRepository.findOne(id);
		board.setTitle(title);
		board.setContent(content);
	}

	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	public Board findOne(Long id) {
		return boardRepository.findOne(id);
	}

	public List<Board> findBytitle(String title) {
		return boardRepository.findByName(title);
	}
}
