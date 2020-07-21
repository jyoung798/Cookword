<template>
	<div>
		<div class="main list-container contents">
			<h1 class="page-header">Welcome CookWorld</h1>
			<LoadingSpinner v-if="isLoading">
				Loading...
			</LoadingSpinner>
			<ul>
				<Menu1List
					v-for="postItem in postItems"
					:key="postItem.id"
					:postItem="postItem"
					@refresh="fetchNotes"
				></Menu1List>
			</ul>
		</div>
		<router-link to="/add" class="create-button">
			write
			<!--<i class 넣으면 됨 -->
		</router-link>
	</div>
</template>

<script>
import Menu1List from '@/components/menu/Menu1List.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';

import { fetchBoardList } from '../api/posts';
export default {
	components: {
		LoadingSpinner,
		Menu1List,
	},
	data() {
		return {
			postItems: [],
			isLoading: false,
		};
	},
	methods: {
		async fetchNotes(id) {
			this.isLoading = true;
			const response = await fetchBoardList(id);
			this.isLoading = false;
			this.postItems = response.data.list;
		},
	},
	created() {
		const id = this.$route.params.id;
		this.id = id;
		console.log(id);
		this.fetchNotes(id);
	},
};
</script>

<style></style>
