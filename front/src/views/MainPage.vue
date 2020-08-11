<template>
	<div class="content">
		<div class="col-m1-2 main-left top">
			<div class="chap1-2">
				<div class="col-title-div">
					<span class="col-title-span">Q&A</span>
				</div>
				<div class="col-content1-2">
					<ul>
						<li class="list-li" v-for="item in list[0]" :key="item.id">
							<h3 style="display: inline;">{{ item.title }}</h3>
							<div id="username">by{{ item.nickname }}</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-m1-2 main-right top">
			<div class="chap1-2">
				<div class="col-title-div">
					<span class="col-title-span">자유게시판</span>
				</div>
				<div class="col-content1-2">
					<ul>
						<li class="list-li" v-for="item in list[1]" :key="item.id">
							<h3 style="display: inline;">{{ item.title }}</h3>
							<div id="username">by{{ item.nickname }}</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="col-m2-3 main-left">
			<div class="chap2-3 top">
				<div class="col-title-div">
					<span
						class="col-title-span"
						style="background-color:rgb(123, 210, 250)"
						>이벤트</span
					>
				</div>
				<div class="col-content2-3">
					<ul>
						<li class="list-li" v-for="item in list[2]" :key="item.id">
							<h3 style="display: inline;">{{ item.title }}</h3>
							<div id="username">by{{ item.nickname }}</div>
						</li>
					</ul>
				</div>
			</div>

			<div class="chap2-3">
				<div class="col-title-div">
					<span class="col-title-span">홍보</span>
				</div>
				<div class="col-content2-3">
					<ul>
						<li class="list-li" v-for="item in list[3]" :key="item.id">
							<h3 style="display: inline;">{{ item.title }}</h3>
							<div id="username">by{{ item.nickname }}</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// import LoadingSpinner from '@/components/common/LoadingSpinner.vue';
// import PostListItem from '@/components/posts/PostListItem.vue';
// import { fetchPosts } from '../api/posts';
import { fetchMain } from '../api/main';
export default {
	components: {
		// PostListItem,
		// LoadingSpinner,
	},
	data() {
		return {
			postItems: [],
			list: [],
			isLoading: false,
		};
	},
	methods: {
		async fetchNotes() {
			this.isLoading = true;
			//const response = await fetchPosts();
			const response = await fetchMain();
			console.log('posts');
			console.log(response);

			this.isLoading = false;
			this.list = response.data.list;
			console.log(this.list[0]);
			console.log(this.list[0][1]);
		},
	},
	created() {
		this.fetchNotes();
	},
};
</script>

<style scoped>
* {
	box-sizing: border-box;
	font-size: 1.2rem;
}
#username {
	text-align: right;
	color: darkorchid;
	margin-right: 1rem;
}
.top {
	margin-bottom: 3rem;
}
.col-title-div {
	color: rgb(47, 48, 51);

	margin-bottom: 0.2rem;
	margin-top: 0.2rem;
}
.col-title-span {
	background-color: rgb(240, 243, 85);
	/* background-color: rgb(123, 210, 250); */
}
.content {
	margin-top: 50px;
	width: 820px;
	min-height: 800px;
	/* border-color: blueviolet;
	border: 2px solid; */
	display: table;
}
.chap1-2 {
	border: 2px solid white;
	background-color: white;
}
.chap2-3 {
	border: 2px solid white;
	background-color: white;
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
	/* border-left: 2px rgb(233, 233, 243) solid !important;
	border-right: 2px #ddd solid !important; */
	border-top: 2px rgb(233, 233, 243) solid !important;
	margin-bottom: 0;
	/* border: 0.7px solid #ddd; */
}
</style>
