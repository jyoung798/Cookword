<template>
	<div class="contents">
		<div class="form-wrapper form-wrapper-sm">
			<form @submit.prevent="submitForm" class="form">
				<div>
					<label for="email">email: </label>
					<input id="email" type="text" v-model="email" />
					<p class="validation-text">
						<span class="warning" v-if="!isUserEmailValid && email">
							Please enter an email address
						</span>
					</p>
				</div>
				<div>
					<label for="pw">pw: </label>
					<input id="pw" type="text" v-model="pw" />
				</div>
				<button v-bind:disabled="!isUserEmailValid || !pw" type="submit">
					로그인
				</button>
			</form>
			<p>{{ log }}</p>
		</div>
	</div>
</template>

<script>
import { validateEmail } from '../utils/validation';

export default {
	data() {
		return {
			email: '',
			pw: '',
			log: '',
		};
	},
	computed: {
		isUserEmailValid() {
			return validateEmail(this.email);
		},
	},
	methods: {
		async submitForm() {
			try {
				const userData = {
					email: this.email,
					pw: this.pw,
				};
				//await 없으면 에러
				await this.$store.dispatch('LOGIN', userData);
				this.initForm();
				this.$router.push('/main');
			} catch (error) {
				console.log(error.response);
				this.log = `아이디 및 비밀번호가 일치하지 않습니다.`;
			}
		},
		initForm() {
			this.email = '';
			this.pw = '';
		},
	},
};
</script>

<style></style>
