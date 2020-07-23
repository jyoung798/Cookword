package jyp.cooksite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.request.LoginDto;
import jyp.cooksite.api.response.LoginUserResponse;
import jyp.cooksite.config.CUserNotFoundException;
import jyp.cooksite.domain.user.User;

import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	
	@Transactional //회원 가입을 진행하는 메소드입니다.
	public Long join(User user) {
		checkDuplicateUser(user);
		//중복되지 않으면 토큰 생성하여 생성 
		
				
		userRepository.save(user);
		return user.getId();
	}

	//같은 이메일인지 확인합니다.
	private void checkDuplicateUser(User user) {
	//	System.out.println("user email : ~ "+user.getEmail());
		User finduser = userRepository.findByEmail(user.getEmail());
		
		if (finduser!=null) {
			throw new IllegalStateException("이미 가입한 회원입니다.");
		}
	}
	
	
	//로그인 로직 입니다.
	public LoginUserResponse login(LoginDto logindto) {
		User user = userRepository.findByEmail(logindto.getEmail());
		if(user == null) {
			throw new RuntimeException("아이디가 존재하지 않습니다.");
		}
		if(!user.getPw().equals(logindto.getPw())) {
			throw new RuntimeException("비밀번호가 맞지 않습니다.");
		}
		return new LoginUserResponse(200,"Login success",user.getNickname(),user.getEmail(),user.getNickname());
		
	}
	
	
	public User loadUserByUsername(String userPk) {
        return userRepository.findByEmail(userPk); //exception : 회원 못찾았을 경우 정의 
    }
	
	
	public List<User> findUsers() {
		return userRepository.findAll();
	}
	
	public List<User> findUsers(String name) { // 친구 찾기 
		return userRepository.findByName(name);
	}
	
	
	public User findOne(Long userid) {
		return userRepository.findOne(userid);
	}

}
