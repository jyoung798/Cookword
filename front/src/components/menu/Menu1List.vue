<template>
	<li class="list-li">
		<h4 id="title">
			<span @click="detailItem">{{ postItem.title }}</span>
		</h4>
		<div id="username">by {{ postItem.nickname }}</div>
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
	methods: {
		async detailItem() {
			const id = this.postItem.id;
			this.$router.push(`/post/detail/${id}`);
		},
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
	margin-right: 1rem;
}

.list-li {
	border-left: 3px #ddd solid !important;
	border-right: 3px #ddd solid !important;
	margin-bottom: 0;
	border: 1px solid #ddd;
}
</style>
