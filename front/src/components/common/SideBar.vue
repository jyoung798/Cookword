<template>
	<nav class="sidebar">
		<nav class="sidecontainer">
			<router-link :to="headerlink">
				<a class="sidebar_menu menu_btn">
					cookWord
				</a>
			</router-link>

			<router-link :to="menulink1">
				<a class="sidebar_menu menu_btn">게시판1</a>
			</router-link>

			<router-link :to="menulink2">
				<a class="sidebar_menu menu_btn">게시판2</a>
			</router-link>

			<router-link :to="menulink3">
				<a class="sidebar_menu menu_btn">게시판3</a>
			</router-link>

			<router-link :to="menulink4">
				<a class="sidebar_menu menu_btn">게시판4</a>
			</router-link>

			<router-link :to="menulink5">
				<a class="sidebar_menu menu_btn">게시판5</a>
			</router-link>
		</nav>
	</nav>
</template>

<script>
export default {
	computed: {
		headerlink() {
			return this.$store.getters.isLogin ? '/main' : '/login';
		},
		menulink1() {
			return '/menu/1';
		},
		menulink2() {
			return '/menu/2';
		},
		menulink3() {
			return '/menu/3';
		},
		menulink4() {
			return '/menu/4';
		},
		menulink5() {
			return '/menu/5';
		},
	},
	methods: {
		// menu1() {
		// 	console.log('메뉴1');
		// 	this.$store.commit('setMemuNum', 1);
		// 	this.$router.push({
		// 		path: '/menu/1',
		// 	});
		// },
		// menu2() {
		// 	console.log('메뉴2');
		// 	this.$store.commit('setMemuNum', 2);
		// 	this.$router.push('/menu/2');
		// },
		// menu3() {
		// 	console.log('메뉴3');
		// 	this.$store.commit('setMemuNum', 3);
		// 	this.$router.push('/menu');
		// },
		// menu4() {
		// 	console.log('메뉴4');
		// 	this.$router.push('/menu');
		// },
		// menu5() {
		// 	console.log('메뉴5');
		// 	this.$router.push('/menu');
		// },
	},
};
</script>

<style>
.sidebar {
	top: 0;
	height: 100%;
	width: 200px;
	background-color: #fff;
	position: fixed !important;
	z-index: 1;
	overflow: auto;

	display: block !important;
}
@media (max-width: 992px) {
	.sidebar {
		display: none !important;
	}
}
.sidecontainer {
	padding-top: 90px;
}
.sidebar_menu {
	padding: 8px 16px;
	float: left;
	width: auto;
	border: none;
	display: block;
	outline: 0;
}
.sidebar_menu {
	width: 100%;
	display: block;
	padding: 8px 16px;
	text-align: left;
	border: none;
	white-space: normal;
	float: none;
	outline: 0;
}
.menu_btn {
	border: none;
	display: inline-block;
	padding: 8px 16px;
	vertical-align: middle;
	overflow: hidden;
	text-decoration: none;
	color: inherit;
	background-color: inherit;
	text-align: center;
	cursor: pointer;
	white-space: nowrap;
}
.menu_btn:hover {
	color: #000 !important;
	background-color: #ccc !important;
}
</style>
