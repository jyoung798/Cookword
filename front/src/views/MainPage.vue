<template>
	<div>
		<div class="main list-container contents">
			<h1 class="page-header">Welcome CookWorld</h1>
			<LoadingSpinner v-if="isLoading">
				Loading...
			</LoadingSpinner>
			<ul>
				<PostListItem
					v-for="postItem in postItems"
					:key="postItem.id"
					:postItem="postItem"
					@refresh="fetchNotes"
				></PostListItem>
			</ul>
		</div>
		<router-link to="/add" class="create-button">
			write
			<!--<i class 넣으면 됨 -->
		</router-link>
	</div>
</template>

<script>
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';
import PostListItem from '@/components/posts/PostListItem.vue';
import { fetchPosts } from '../api/posts';
export default {
	components: {
		PostListItem,
		LoadingSpinner,
	},
	data() {
		return {
			postItems: [],
			isLoading: false,
		};
	},
	methods: {
		async fetchNotes() {
			this.isLoading = true;
			const response = await fetchPosts();
			this.isLoading = false;
			this.postItems = response.data.list;
		},
	},
	created() {
		this.fetchNotes();
	},
};
</script>

<style></style>
