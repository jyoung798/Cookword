import axios from 'axios';

const baseurl = axios.create({
	baseURL: process.env.VUE_APP_API_URL,
});

function createUser(userData) {
	return baseurl.post('user/join', userData);
}

export { createUser };
