//공용 게시판 CRUD API
import { posts } from './index';
//공통 게시판 글 목록 불러오기 api 호출
function fetchPosts() {
	return posts.get('/');
}
//공통 게시판 특정 글1개를 조회하는 api
function fetchPost(postId) {
	return posts.get('/' + postId);
}

//공통 게시판 글 쓰기 api 호출
function createPost(postData) {
	return posts.post('/', postData);
}

//게시판 글 삭제 api
function deletePost(postId) {
	return posts.delete('/' + postId);
}
//게시판 글 수정 api
function editPost(postId, postData) {
	return posts.put('/' + postId, postData);
}

function upload(file) {
	return posts.post('/upload', file);
}
//메뉴에 따라 게시판 목록 호출
function fetchBoardList(menuNum) {
	return posts.get('/menu/' + menuNum);
}

//게시판 상세 페이지 호출
function fetchBoardDetail(menuNum) {
	return posts.get('/detail/' + menuNum);
}
export {
	fetchPosts,
	createPost,
	deletePost,
	fetchPost,
	editPost,
	upload,
	fetchBoardList,
	fetchBoardDetail,
};
