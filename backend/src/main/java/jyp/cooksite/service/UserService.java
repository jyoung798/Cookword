package jyp.cooksite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.exception.DuplicatedUserException;
import jyp.cooksite.api.exception.UserNotFoundException;
import jyp.cooksite.api.request.LoginDto;
import jyp.cooksite.api.response.LoginUserResponse;

import jyp.cooksite.domain.user.User;

import jyp.cooksite.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	
	@Transactional //ȸ�� ������ �����ϴ� �޼ҵ��Դϴ�.
	public Long join(User user) {
		checkDuplicateUser(user);
		//�ߺ����� ������ ��ū �����Ͽ� ���� 
		
				
		userRepository.save(user);
		return user.getId();
	}

	//���� �̸������� Ȯ���մϴ�.
	private void checkDuplicateUser(User user) {
	//	System.out.println("user email : ~ "+user.getEmail());
		Optional<User> finduser = userRepository.findByEmail(user.getEmail() ) ; //.orElseThrow(DuplicatedUserException::new);
		//finduser.isPresent()
		
		if (finduser!=null) {
			throw new DuplicatedUserException("�̹� ������ ȸ���Դϴ�.");
		}
	}
	
	
	//�α��� ���� �Դϴ�.
//	public LoginUserResponse login(LoginDto logindto) {
//		User user = userRepository.findByEmail(logindto.getEmail());
//		if(user == null) {
//			throw new RuntimeException("���̵� �������� �ʽ��ϴ�.");
//		}
//		if(!user.getPw().equals(logindto.getPw())) {
//			throw new RuntimeException("��й�ȣ�� ���� �ʽ��ϴ�.");
//		}
//		return new LoginUserResponse(200,"Login success",user.getNickname(),user.getEmail(),user.getNickname());
//		
//	}
	
	
	public UserDetails loadUserByUsername(String userPk) {
		return userRepository.findByEmail(userPk).orElseThrow(UserNotFoundException::new); 
    }
	
	
	public List<User> findUsers() {
		return userRepository.findAll();
	}
	
//	public List<User> findUsers(String name) { // ģ�� ã�� 
//		return userRepository.findByName(name);
//	}
	
	
	public Optional<User> findOne(Long userid) {
		return userRepository.findById(userid);
	}

}
