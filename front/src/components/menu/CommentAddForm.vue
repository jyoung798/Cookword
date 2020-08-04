<template>
	<div class="dmain" style="margin-top: 10px;">
		<div class="dcontent">
			<div>
				<div class="form-comment">
					<form @submit.prevent="submitComment">
						<div class="comment">
							<label for="comment">답변 하기</label>
							<textarea
								id="comment"
								type="text"
								rows="5"
								v-model="content"
							></textarea>
							<button type="submit">확인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { createComment } from '../../api/comment';
export default {
	data() {
		return {
			content: '',
			id: null,
		};
	},
	methods: {
		// 		function createComment(postNum, commentData) {
		// 	return posts.post('/detail/addcomment/' + postNum, commentData);
		// }
		async submitComment() {
			try {
				//const response =
				await createComment(this.id, {
					contents: this.content,
				});
				this.$router.go(); //댓글만 불러오도록 수정 해야함
				//이벤트를 BoardDetail로 올리고 -> 댓글리스트 다시 호출하도록 수정 해야함
			} catch (error) {
				console.log(error);
				//this.logMessage = error.response.data.msg;
			}
		},
	},
	created() {
		const id = this.$route.params.id;
		this.id = id;
		console.log('댓글추가폼');
		console.log(id);
	},
};
</script>

<style>
.form-comment {
	width: 95%;
	height: 100%;
}
.form-comment label {
	width: 100%;
	display: block;
	margin-bottom: 0.5rem;
	color: #364f6b;
	font-size: 70%;
}

.form-comment textarea,
.form-comment input {
	font-family: inherit;
	font-size: 100%;
	width: 100%;
	border: 1px solid #dae1e7;
	box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
	padding: 0.5rem 0.75rem;
	margin-bottom: 1rem;
}
.form-comment textarea {
	height: 200px;
	font-size: 80%;
}
.comment {
	max-width: 1020px;
	margin: 0 auto;
	padding: 0 5px;
	width: 100%;
}
</style>
