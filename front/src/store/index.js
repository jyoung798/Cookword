import Vue from 'vue';
import Vuex from 'vuex';
import { getAuthFromCookie, getUserFromCookie } from '@/utils/cookies.js';
import { loginUser } from '@/api/auth';
import { saveAuthToCookie, saveUserToCookie } from '@/utils/cookies.js';
Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		email: getUserFromCookie() || '',
		nickname: '',
		token: getAuthFromCookie() || '',
	},
	getters: {
		isLogin(state) {
			return state.email !== '';
		},
	},
	mutations: {
		setEmail(state, email) {
			state.email = email;
		},
		setNickname(state, nickname) {
			state.nickname = nickname;
		},
		setToken(state, token) {
			state.token = token;
		},
		clearNickname(state) {
			state.nickname = '';
		},
		clearEmail(state) {
			state.email = '';
		},
		clearToken(state) {
			state.token = '';
		},
	},
	actions: {
		//LOGIN(context)
		async LOGIN({ data }, userData) {
			const response = await loginUser(userData);

			this.commit('setToken', response.data.token);
			this.commit('setEmail', response.data.email);
			saveAuthToCookie(response.data.token);
			saveUserToCookie(response.data.email);
			return data; //리턴 없어도 프로미스가 리턴됨
		},
	},
});
