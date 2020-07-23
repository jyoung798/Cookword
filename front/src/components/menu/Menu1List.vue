<template>
	<li class="list-li">
		<h4 id="title">{{ postItem.title }}</h4>
		<div id="username">by 사용자 이름</div>
	</li>
</template>
<script>
export default {
	props: {
		postItem: {
			type: Object,
			required: true, //값이 내려오는지 체크 없으면 에러
		},
	},
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
#title {
	margin-left: 1rem;
	margin-bottom: 0.2rem;
	color: #1a446b;
}
#username {
	text-align: right;
	color: darkorchid;
}

.list-li {
	border-left: 3px #ddd solid !important;
	border-right: 3px #ddd solid !important;
	margin-bottom: 0;
	border: 1px solid #ddd;
}
</style>
