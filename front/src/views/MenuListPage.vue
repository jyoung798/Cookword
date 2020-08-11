<template>
	<div class="main">
		<div id="list-container">
			<h1>메뉴1에 대한 리스트 목록이 나와야 합니다</h1>
			<div id="list-header">
				<span style="font-size: 1.5rem;">제목</span>
				<router-link to="/add">
					<span style="float: right;font-size: 1.5rem;">글쓰기</span>
				</router-link>

				<div class="search">
					<input placeholder="검색어" class="input" type="search" />
					<span class="input-btn">검색</span>
				</div>
			</div>
			<div id="list-content">
				<LoadingSpinner v-if="isLoading">
					Loading...
				</LoadingSpinner>
				<ul class="list-ul">
					<Menu1List
						v-for="postItem in postItems"
						:key="postItem.id"
						:postItem="postItem"
						@refresh="fetchNotes"
					></Menu1List>
				</ul>
			</div>

			<div class="paging">
				<Paginate
					:page-count="totalpage"
					:page-range="3"
					:margin-pages="2"
					:click-handler="clickCallback"
					:prev-text="'Prev'"
					:next-text="'Next'"
					:container-class="'pagination'"
					:page-class="'page-item'"
				>
				</Paginate>
			</div>
		</div>
		<div class="footer"></div>
	</div>
</template>

<script>
import Menu1List from '@/components/menu/Menu1List.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';

import { fetchBoardList } from '../api/posts';

import Paginate from 'vuejs-paginate';

export default {
	props: {
		// pageCount: { type: [Number, String] },
	},
	components: {
		LoadingSpinner,
		Paginate,

		Menu1List,
	},
	data() {
		return {
			postItems: [],
			isLoading: false,
			totalpage: 0,
		};
	},
	methods: {
		async fetchNotes(id) {
			this.isLoading = true;
			const { data } = await fetchBoardList(id, this.$route.query);

			this.totalpage = data.page.totalPages;

			this.isLoading = false;
			console.log(data);
			//this.postItems = response.data.list;
			this.postItems = data.page.content;
		},
		/**
		 * 화살표 함수일 경우 바깥의 this 를 찾는다.
		 * pagenaiton라이브러리안  함수(a,b,..,callback()){
		 *  callback(선택된 페이지 num 계산해서 대입);
		 *
		 * }
		 * clickCallback함수가 callback 함수가 된다.
		 *함수의 내부함수, 함수의 콜백함수에 사용되는 this는 window입니다.
		 */
		async clickCallback(pageNum) {
			console.log(pageNum);
			console.log(this);
			console.log(window);

			this.$route.query.page = pageNum;
			const { data } = await fetchBoardList(this.id, this.$route.query);
			this.postItems = data.page.content;

			//this.$router.push('/menu/' + this.id + '?page=' + pageNum);
		},
	},
	created() {
		console.log(this.$route);
		console.log(this.$route.query);
		const id = this.$route.params.id;
		this.id = id;
		console.log(id);
		this.fetchNotes(id);
	},
};
</script>

<style>
.input {
	border: 1px solid #ddd;
	display: table-cell;
	height: 30px;
	width: 94%;
}
.input-btn {
	width: 6%;
}
.search {
	margin-top: 7px;
	width: 100%;
	position: relative;
	display: table;
	border-collapse: separate;
}
.main {
	font-family: Helvetica Neue, Helvetica, Arial, 'Apple SD Gothic Neo',
		'Malgun Gothic', Dotdum;
	padding-right: 30px;
}
@media (max-width: 992px) {
	.main {
		width: auto;
		position: relative;
		padding: 60px 0;
		margin-left: 0;
		margin-top: 30px;
		padding-right: 30px;
		padding-left: 30px;
	}
}
@media (min-width: 992px) {
	#list-container {
		width: 45rem;
	}
}
#list-header {
	margin-bottom: 10px;
}
#list-container {
	/* width: inherit; */
	font-size: 14px;
	border-radius: 0 !important;
	border: 1px solid transparent;
}
.footer {
	height: 10rem;
}
.pagination {
}
.page-item {
}
</style>
