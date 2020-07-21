import axios from 'axios';
// import store from '../store/index';
import { setInterceptors } from './common/interceptors';

function createInstance() {
	return axios.create({
		baseURL: process.env.VUE_APP_API_URL,
	});
}
//액시오스 초기화
function createInstanceWithAuth(url) {
	const instance = axios.create({
		baseURL: `${process.env.VUE_APP_API_URL}${url}`,
		// headers: {
		// 	'Content-Type': 'application/json',
		// },
	});
	return setInterceptors(instance);
}

export const instance = createInstance();
export const posts = createInstanceWithAuth('posts');
