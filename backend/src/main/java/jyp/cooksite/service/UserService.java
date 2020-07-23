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
		User finduser = userRepository.findByEmail(user.getEmail());
		
		if (finduser!=null) {
			throw new IllegalStateException("�̹� ������ ȸ���Դϴ�.");
		}
	}
	
	
	//�α��� ���� �Դϴ�.
	public LoginUserResponse login(LoginDto logindto) {
		User user = userRepository.findByEmail(logindto.getEmail());
		if(user == null) {
			throw new RuntimeException("���̵� �������� �ʽ��ϴ�.");
		}
		if(!user.getPw().equals(logindto.getPw())) {
			throw new RuntimeException("��й�ȣ�� ���� �ʽ��ϴ�.");
		}
		return new LoginUserResponse(200,"Login success",user.getNickname(),user.getEmail(),user.getNickname());
		
	}
	
	
	public User loadUserByUsername(String userPk) {
        return userRepository.findByEmail(userPk); //exception : ȸ�� ��ã���� ��� ���� 
    }
	
	
	public List<User> findUsers() {
		return userRepository.findAll();
	}
	
	public List<User> findUsers(String name) { // ģ�� ã�� 
		return userRepository.findByName(name);
	}
	
	
	public User findOne(Long userid) {
		return userRepository.findOne(userid);
	}

}
