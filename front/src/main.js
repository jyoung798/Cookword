import Vue from 'vue';
import App from './App.vue';
import router from './routes/index';
import store from './store/index.js';
//import 'bootstrap';
// import 'bootstrap/dist/css/bootstrap.css';

//import VueQuillEditor from 'vue-quill-editor';

//import 'quill/dist/quill.core.css'; // import styles
//import 'quill/dist/quill.snow.css'; // for snow theme
//import 'quill/dist/quill.bubble.css'; // for bubble theme

//Vue.use(VueQuillEditor /* { default global options } */);
new Vue({
	render: h => h(App),
	router,
	store,
}).$mount('#app');
