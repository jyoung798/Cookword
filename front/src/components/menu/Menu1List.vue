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
				<ul class="list-ul">
					<li class="list-li">
						<h4 id="title">맛있는 빵 만드는 법</h4>
						<div id="username">by 사용자 이름</div>
					</li>
					<li class="list-li">
						<h4 id="title">맛있는 빵 만드는 법</h4>
						<div id="username">by 사용자 이름</div>
					</li>
					<li class="list-li">
						<h4 id="title">맛있는 빵 만드는 법</h4>
						<div id="username">by 사용자 이름</div>
					</li>
					<li class="list-li">
						<h4 id="title">맛있는 빵 만드는 법</h4>

						<div id="username">by 사용자 이름</div>
					</li>
					<li class="list-li">
						<h4 id="title">맛있는 빵 만드는 법</h4>
						<div id="username">by 사용자 이름</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</template>
<script>
export default {
	data() {
		return {
			id: '',
		};
	},

	created() {
		const id = this.$route.params.id;
		this.id = id;
		console.log(id);
	},
};
</script>

<style scoped>
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
#title {
	margin-left: 1rem;
	margin-bottom: 0.2rem;
	color: #1a446b;
}
#username {
	text-align: right;
	color: darkorchid;
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

#list-header {
	margin-bottom: 10px;
}
#list-container {
	width: inherit;
	font-size: 14px;
	border-radius: 0 !important;
	border: 1px solid transparent;
}
.list-ul {
}
.list-li {
	border-left: 3px #ddd solid !important;
	border-right: 3px #ddd solid !important;
	margin-bottom: 0;
	border: 1px solid #ddd;
}
</style>
