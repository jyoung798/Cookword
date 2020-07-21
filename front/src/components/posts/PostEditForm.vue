<template>
	<div class="contents">
		<h1 class="page-header">Edit</h1>
		<div class="form-wrapper">
			<form class="form" @submit.prevent="submitForm">
				<div>
					<label for="title">Title:</label>
					<input id="title" type="text" v-model="title" />
				</div>
				<div>
					<label for="contents">Contents:</label>
					<textarea id="contents" type="text" rows="5" v-model="contents" />
					<p
						v-if="!isContentsValid"
						class="validation-text warning isContentTooLong"
					>
						글자 길이 제한 200
					</p>
				</div>
				<button type="submit" class="btn">수정완료</button>
			</form>
			<p class="log">
				{{ logMessage }}
			</p>
		</div>
	</div>
</template>

<script>
import { fetchPost, editPost } from '../../api/posts';
export default {
	data() {
		return {
			title: '',
			contents: '',
			logMessage: '',
		};
	},
	computed: {
		isContentsValid() {
			return this.contents.length <= 200;
		},
	},
	methods: {
		async submitForm() {
			const id = this.$route.params.id;
			try {
				await editPost(id, {
					title: this.title,
					contents: this.contents,
				});
				this.$router.push('/main');
			} catch (error) {
				console.log(error);
			}
		},
	},
	async created() {
		const id = this.$route.params.id;
		const { data } = await fetchPost(id);
		this.title = data.data.title;
		this.contents = data.data.content;
		console.log(data);
	},
};
</script>

<style scoped>
.form-wrapper .form {
	width: 100%;
}
.btn {
	color: white;
}
</style>
