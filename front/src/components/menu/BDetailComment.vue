<template>
	<div class="dmain">
		<div class="dcontent" style="margin-bottom: 50px;">
			<div class="panel-header">
				<div class="duser">{{ propscomment.username }}</div>
			</div>
			<div>
				<div class="dmain2">
					<div class="content">{{ propscomment.content }}</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		propscomment: {
			type: Object,
			required: true, //값이 내려오는지 체크 없으면 에러
		},
	},
};
</script>

<style>
.dmain {
	margin-top: 2rem;
	margin-right: 2rem;
	margin-left: 0.3rem;
	max-width: 1111px;
}
.dcontent {
	border-radius: 8px !important;
	border-color: #ddd;
	border: 1px solid rgb(236, 222, 222);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}
.panel-header {
	padding: 10px 15px;
	border-bottom: 1px solid rgb(236, 222, 222);
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
}

.duser {
	margin-left: 1rem;
	margin-bottom: 1rem;
}
.content {
	margin-left: 1rem;
}
@media (min-width: 992px) {
	.dmain {
		width: 46rem;
	}
}
</style>
