//공용 게시판 CRUD API
import { posts } from './index';

//상세 페이지 댓글 작성
function createComment(postNum, commentData) {
	return posts.post('/detail/addcomment/' + postNum, commentData);
}
//상세 페이지 댓글 리스트 호출
function fetchComments(postNum) {
	return posts.get('/detail/comments/' + postNum);
}
export { fetchComments, createComment };
