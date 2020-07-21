<template>
	<div class="app">
		<AppHeader></AppHeader>
		<SideBar></SideBar>
		<div class="app-contents">
			<router-view :key="$route.fullPath"></router-view>
		</div>
	</div>
</template>

<script>
import AppHeader from '@/components/common/AppHeader.vue';
import SideBar from '@/components/common/SideBar.vue';

export default {
	components: {
		AppHeader,
		SideBar,
	},
};
</script>

<style>
@import './css/base.css';
@import './css/common.css';
</style>
