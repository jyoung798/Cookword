<template>
	<div class="content">
		<div class="col-m1-2 main-left top">
			<div class="chap1-2">
				<div class="col-title">Title</div>
				<div class="col-content1-2">
					<ul>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-m1-2 main-right top">
			<div class="chap1-2">
				<div class="col-title">Title</div>
				<div class="col-content1-2">
					<ul>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-m2-3 main-left">
			<div class="chap2-3 top">
				<div class="col-title">Title</div>
				<div class="col-content2-3">
					<ul>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
					</ul>
				</div>
			</div>

			<div class="chap2-3">
				<div class="col-title">Title</div>
				<div class="col-content2-3">
					<ul>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="col-m1-3 main-right">
			<div class="chap1-3 top">
				<div class="col-title">Title</div>
				<div class="col-content1-3">
					<ul>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
					</ul>
				</div>
			</div>

			<div class="chap1-3">
				<div class="col-title">Title</div>
				<div class="col-content1-3">
					<ul>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
						<li class="list-li">zz</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- <div>
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
		</router-link>
	</div> -->
</template>

<script>
// import LoadingSpinner from '@/components/common/LoadingSpinner.vue';
// import PostListItem from '@/components/posts/PostListItem.vue';
import { fetchPosts } from '../api/posts';
export default {
	components: {
		// PostListItem,
		// LoadingSpinner,
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
			console.log('posts');
			console.log(response.data.list);

			this.isLoading = false;
			this.postItems = response.data.list;
		},
	},
	created() {
		// this.fetchNotes();
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
}
.top {
	margin-bottom: 3rem;
}
.col-title {
	color: rgb(19, 61, 197);
	margin-bottom: 0.2rem;
	margin-top: 0.2rem;
}
.content {
	margin-top: 50px;
	width: 820px;
	min-height: 800px;
	/* border-color: blueviolet;
	border: 2px solid; */
	display: table;
}
.col-m1-2 {
	/* border: 2px solid; */
	position: relative;
	min-height: 1px;
}
@media (min-width: 768px) {
	.col-m1-2 {
		width: 50%;
	}

	.main-left,
	.main-right {
		float: left;
	}
	.col-m2-3 {
		width: 66.66666667%;
	}
	.col-m1-3 {
		width: 33.33333333%;
	}
}
.main-right {
	padding-left: 15px;
	padding-right: 0;
}
.main-left {
	padding-left: 0;
	padding-right: 15px;
}
.list-li {
	border-left: 2px rgb(233, 233, 243) solid !important;
	border-right: 2px #ddd solid !important;
	margin-bottom: 0;
	border: 0.7px solid #ddd;
}
</style>
