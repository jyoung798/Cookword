<template>
	<div class="dmain">
		<div><h4>게시판</h4></div>
		<div class="dcontent">
			<div class="dcontentheader panel-header">
				<div class="duser">
					<div id="userimg">
						<ion-icon
							style="font-size:3rem;"
							name="person-circle-outline"
						></ion-icon>
					</div>
					<div style="">
						<h4 style="margin-top: 1rem;">{{ propscontent.username }}</h4>
					</div>
					<div class="edit">
						<div style="float: right;">
							<ion-icon
								name="create-outline"
								@click="editItem"
								class="icon"
							></ion-icon>
							<ion-icon
								name="trash-outline"
								@click="deleteItem"
								class="icon"
							></ion-icon>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div class="dmain2">
					<div class="tag"></div>
					<div class="title">{{ propscontent.title }}</div>

					<div class="content" v-html="propscontent.content">
						<!--프롭스로 내려온 content 바로 v-html에 넣자-->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { deletePost } from '../../api/posts';
export default {
	data() {
		return {
			pcontent: this.propscontent.content,
		};
	},
	props: {
		propscontent: {
			type: Object,
			required: true, //값이 내려오는지 체크 없으면 에러
		},
	},
	methods: {
		async deleteItem() {
			if (confirm('정말 삭제 하시겠습니까?')) {
				await deletePost(this.propscontent.id);
				//this.$router.push('/menu/1');
				// this.$emit('refresh');
				this.$router.go(-1);
			}
		},
		async editItem() {
			const id = this.propscontent.id;
			this.$router.push(`/post/${id}`);
		},
	},
};
</script>

<style scoped>
#userimg {
	align-self: center;
}
.dmain {
	margin-top: 2rem;
	margin-right: 2rem;
	margin-left: 0.3rem;
	max-width: 1111px;
}
.panel-header {
	padding: 10px 15px;
	border-bottom: 1px solid rgb(236, 222, 222);
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
	height: 5rem;
}
.dcontent {
	border-radius: 8px !important;
	border-color: #ddd;
	border: 1px solid rgb(236, 222, 222);
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}
.title {
	margin-top: 1rem;
	margin-left: 1rem;
	margin-bottom: 1rem;
	border-bottom: 1px solid rgb(236, 222, 222);
}
.duser {
	display: flex;
	margin-left: 1rem;
	margin-bottom: 1rem;
	height: 100%;
}
.edit {
	flex: 2;
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
