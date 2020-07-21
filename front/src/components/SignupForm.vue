<template>
	<div class="contents">
		<div class="form-wrapper form-wrapper-sm">
			<form @submit.prevent="submitForm" class="form">
				<div>
					<label for="email">email : </label>
					<input id="email" type="text" v-model="email" />
				</div>

				<div>
					<label for="pw">pw : </label>
					<input id="pw" type="text" v-model="pw" />
				</div>
				<div>
					<label for="nickname">nickname : </label>
					<input id="nickname" type="text" v-model="nickname" />
				</div>
				<button type="submit">회원 가입</button>
			</form>
			<p>{{ logMessage }}</p>
		</div>
	</div>
</template>

<script>
import { createUser } from '../api/auth';
export default {
	data() {
		return {
			pw: '',
			email: '',
			nickname: '',

			logMessage: '',
		};
	},
	methods: {
		async submitForm() {
			console.log('회원가입');
			const userDate = {
				email: this.email,
				pw: this.pw,
				nickname: this.nickname,
			};
			//const response =
			const { data } = await createUser(userDate);
			console.log(data.nickname);
			this.logMessage = `${data.nickname}님 환영합니다.`;
			this.initForm();
		},
		initForm() {
			this.email = '';
			this.pw = '';
			this.nickname = '';
		},
	},
};
</script>

<style></style>
