package jyp.cooksite.api;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jyp.cooksite.api.request.BoardDto;
import jyp.cooksite.api.request.CreateUserRequest;
import jyp.cooksite.api.request.LoginDto;
import jyp.cooksite.api.response.CreateUserResponse;
import jyp.cooksite.api.response.ListResult;
import jyp.cooksite.api.response.LoginUserResponse;
import jyp.cooksite.api.response.SingleResult;
import jyp.cooksite.api.service.ResponseService;
import jyp.cooksite.config.JwtTokenProvider;
import jyp.cooksite.domain.commonboard.Board;
import jyp.cooksite.domain.user.User;
import jyp.cooksite.exception.CEmailSigninFailedException;
import jyp.cooksite.repository.BoardRepository;
import jyp.cooksite.repository.UserRepository;
import jyp.cooksite.service.BoardService;
import jyp.cooksite.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

	private final BoardRepository boardRepository;
	private final ResponseService responseService;
	private final BoardService boardService;
	
	//공통 게시판 목록 가져오기
		@ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
		})
	@GetMapping("/posts")
	public ListResult<Board> fetchPosts() {
		
		 return responseService.getListResult(boardService.findAll()); //list.안에 Board 객체 
	}
	
	//공통 게시판 글쓰기 
		@PostMapping("/posts")
		public SingleResult<Long> post(@RequestBody BoardDto boardDto) {
			
			Board board = Board.builder()
					.title(boardDto.getTitle())
					.content(boardDto.getContents())
					.createdDate(LocalDate.now())
					.build();
					
				
			Long id=boardService.post(board);
		
			return responseService.getSingleResult(id);
		}
		
		@DeleteMapping("/posts/{id}")
	    public SingleResult<Long> delete(@PathVariable("id") Long id) {
	        boardService.delete(id);
	        
	        return responseService.getSingleResult(id);
	    }

		//특정 게시물 수정시 
		@GetMapping("/posts/{id}")
		public SingleResult<Board> fetchPost(@PathVariable("id") Long id){
			
			Board board= boardService.findOne(id);
			
			
			return responseService.getSingleResult(board);
		}
		
		@PutMapping("/posts/{id}")
		public SingleResult<Long> editPost(@PathVariable("id") Long id,@RequestBody BoardDto boardDto){
			
			boardService.update(id, boardDto.getTitle(), boardDto.getContents());
			return responseService.getSingleResult(id);
		}
	
		@PostMapping(value="/posts/upload")
		@ResponseBody
		public SingleResult<String> uploadfile(@RequestParam("file") MultipartFile multipartFile){
			System.out.println("파일 업로드 ");
			String fileRoot = "C:\\spring\\Myproject\\workspace2\\Cookword\\front\\src\\image\\";	//저장될 외부 파일 경로
			String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
			
			String savedFileName = UUID.randomUUID() + extension;
			File targetFile = new File(fileRoot + savedFileName);
			String url=null;
			try {
				InputStream fileStream = multipartFile.getInputStream();
				org.apache.commons.io.FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
				url="/image/"+savedFileName;
				
			//	jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
//				jsonObject.addProperty("responseCode", "success");
					
			} catch (IOException e) {
//				FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//				jsonObject.addProperty("responseCode", "error");
				System.out.println("파일 업로드 에러 ");
				e.printStackTrace();
			}
			
			return responseService.getSingleResult(url);
			
		}

	

}
