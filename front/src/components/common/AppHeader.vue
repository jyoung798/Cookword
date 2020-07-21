<template>
	<header>
		<div>
			<router-link :to="headerlink" class="logo">
				cookWord
			</router-link>
		</div>
		<div class="navigations">
			<template v-if="isUserLogin">
				<span class="nickname">{{ $store.state.email }}</span>
				<a href="javascript:;" @click="logoutUser">로그아웃</a>
			</template>
			<template v-else>
				<router-link to="/login">로그인 </router-link>
				<router-link to="/signup"> 회원가입</router-link>
			</template>
		</div>
	</header>
</template>

<script>
import { deleteCookie } from '../../utils/cookies';
export default {
	computed: {
		isUserLogin() {
			return this.$store.getters.isLogin;
		},
		headerlink() {
			return this.$store.getters.isLogin ? '/main' : '/login';
		},
	},
	methods: {
		logoutUser() {
			this.$store.commit('clearEmail');
			this.$store.commit('clearToken');

			deleteCookie('til_auth');
			deleteCookie('til_user');
			this.$router.push('/login');
		},
	},
};
</script>

<style>
header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 20px;
	background-color: #927dfc;
	z-index: 2;
	box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
}
@media (min-width: 992px) {
	header {
		display: none !important;
	}
}
.logo > span {
	font-size: 14px;
	font-weight: normal;
}
.nickname {
	color: white;
}
</style>
