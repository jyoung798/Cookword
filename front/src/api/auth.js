//로그인 회원가입 api 호출
import { instance } from './index';
//회원 가입 api 호출
function createUser(userData) {
	return instance.post('user/join', userData);
}

//회원 로그인 api 호출
function loginUser(userData) {
	return instance.post('user/login', userData);
}

export { createUser, loginUser };
