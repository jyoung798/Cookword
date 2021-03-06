import Vue from 'vue';
import Vuex from 'vuex';
import {
	getAuthFromCookie,
	getUserFromCookie,
	getNickFromCookie,
} from '@/utils/cookies.js';
import { loginUser } from '@/api/auth';
import {
	saveAuthToCookie,
	saveUserToCookie,
	saveNickToCookie,
} from '@/utils/cookies.js';
Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		email: getUserFromCookie() || '',
		nickname: getNickFromCookie() || '',
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
			console.log(response.data);
			this.commit('setToken', response.data.data.token);
			this.commit('setEmail', response.data.data.email);
			this.commit('setNickname', response.data.data.nickname);

			saveAuthToCookie(response.data.data.token);
			saveUserToCookie(response.data.data.email);
			saveNickToCookie(response.data.data.nickname);
			return data; //리턴 없어도 프로미스가 리턴됨
		},
	},
});
