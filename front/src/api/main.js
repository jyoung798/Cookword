import { instance } from './index';

//회원 로그인 api 호출
function fetchMain() {
	return instance.get('/cooksite');
}

export { fetchMain };
