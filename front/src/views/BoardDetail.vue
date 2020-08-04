<template>
	<div>
		<BDetailContent
			v-if="detailcontent !== {}"
			:propscontent="detailcontent"
			@refresh="fetchdetail(this.id)"
		></BDetailContent>
		<br />

		<span><h5 id="rep">댓글</h5></span>
		<CommentAddForm></CommentAddForm>
		<BDetailComment
			v-for="comment in comments"
			:key="comment.id"
			:propscomment="comment"
		></BDetailComment>
	</div>
</template>

<script>
import BDetailContent from '@/components/menu/BDetailContent.vue';
import BDetailComment from '@/components/menu/BDetailComment.vue';
import CommentAddForm from '@/components/menu/CommentAddForm.vue';
import { fetchBoardDetail } from '../api/posts';
import { fetchComments } from '../api/comment';
export default {
	data() {
		return {
			detailcontent: {}, // Object
			id: '',
			comments: [],
		};
	},
	components: {
		BDetailContent,
		BDetailComment,
		CommentAddForm,
	},
	methods: {
		async fetchdetail(id) {
			try {
				const response = await fetchBoardDetail(id);
				this.detailcontent = response.data.data;
				console.log(response);
			} catch (error) {
				console.log(error);
			}
		},
		async fetchcomments(id) {
			try {
				const response = await fetchComments(id);
				// this.detailcontent = response.data.data;
				this.comments = response.data.list; // 배열안에 객체{username, contetnt}
				console.log(response);
			} catch (error) {
				console.log(error);
			}
		},
	},

	created() {
		this.id = this.$route.params.id;

		this.fetchdetail(this.id);
		this.fetchcomments(this.id);
	},
};
</script>

<style>
#rep {
	margin-bottom: 1rem;
}
</style>
