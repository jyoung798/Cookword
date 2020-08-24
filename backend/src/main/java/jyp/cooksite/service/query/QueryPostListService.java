package jyp.cooksite.service.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jyp.cooksite.api.response.dto.PostListResponse;
import jyp.cooksite.domain.commonboard.Post;
import jyp.cooksite.service.UserActionService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryPostListService {

	private final UserActionService userActionService;
	
	public List<PostListResponse> showScrapList(String eamil){
	 List<Post> posts= userActionService.scrapList(eamil);
       
     List<PostListResponse> scraplist = posts.stream()
     		.map(o-> new PostListResponse(o))
     		.collect(Collectors.toList());
	
     return scraplist;
	}
}
