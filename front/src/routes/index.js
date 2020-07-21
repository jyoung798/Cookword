import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store/index';
// import LoginPage from '../views/LoginPage';
// import SignupPage from '../views/SignupPage';

Vue.use(VueRouter);

//export default
const router = new VueRouter({
	mode: 'history',
	routes: [
		{
			path: '/',
			redirect: '/login',
		},
		{
			path: '/main',
			component: () => import('../views/MainPage.vue'),
			meta: { auth: true },
		},
		{
			path: '/signup',
			component: () => import('../views/SignupPage.vue'),
		},
		{
			path: '/login',
			component: () => import('../views/LoginPage.vue'),
		},

		{
			path: '/add',
			component: () => import('../views/PostAddPage.vue'),
			meta: { auth: true },
		},
		{
			path: '/post/:id',
			component: () => import('../views/PostEditPage.vue'),
			meta: { auth: true },
		},
		{
			path: '/menu/:id',
			component: () => import('../views/MenuListPage.vue'),
		},

		{
			path: '*',
			component: () => import('../views/NotFoundPage.vue'),
		},
	],
});
//from  : 현재 , to : 이동하려는 페이지,next 이동
router.beforeEach((to, from, next) => {
	if (to.meta.auth && !store.getters.isLogin) {
		console.log('인증이 필요합니다');
		next('/login');
		return; //밑에 까지 실행 되는걸 막는다.
	}
	next();
	console.log(to);
	console.log(from);
});
export default router;
