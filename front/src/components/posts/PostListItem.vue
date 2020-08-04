<template>
	<li>
		<div class="post-title">
			{{ postItem.title }}
		</div>
		<div class="post-contents" v-html="content">
			<!--태그속성 반영하기위해 vhtml사용-->
			<!-- {{ postItem.content }} -->
		</div>
		<div class="post-time">
			{{ postItem.createdAt }}
			<ion-icon name="create-outline" class="icon" @click="editItem"></ion-icon>
			<ion-icon
				name="trash-outline"
				class="icon"
				@click="deleteItem"
			></ion-icon>
		</div>
	</li>
</template>

<script>
import { deletePost } from '../../api/posts';
export default {
	data() {
		return {
			content: this.postItem.content,
		};
	},
	props: {
		postItem: {
			type: Object,
			required: true, //값이 내려오는지 체크 없으면 에러
		},
	},
	methods: {
		async deleteItem() {
			if (confirm('정말 삭제 하시겠습니까?')) {
				await deletePost(this.postItem.id);
				this.$emit('refresh');
			}
		},
		async editItem() {
			const id = this.postItem.id;
			this.$router.push(`/post/${id}`);
		},
	},
};
</script>

<style>
.icon {
	font-size: 1.2rem;
}
</style>
