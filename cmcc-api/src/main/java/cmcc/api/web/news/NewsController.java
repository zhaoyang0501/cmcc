package cmcc.api.web.news;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cmcc.api.web.exam.dto.ExamDto;
import cmcc.api.web.news.dto.NewsListDto;
import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.core.exam.serivce.ExamService;
import cmcc.core.news.service.NewsService;
import cmcc.core.sys.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import cmcc.core.news.entity.Comment;
import cmcc.core.news.entity.News;
import cmcc.core.news.entity.NewsCategory;;
@Api(value = "新闻模块")
@RestController
@RequestMapping("news")
public class NewsController {
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private NewsService newsService;
	
	@ApiOperation(value = "获取全部分类下新闻",notes="获取全部分类下新闻", response=NewsListDto.class)
	@RequestMapping(value = "/news/page/{page}", method = RequestMethod.GET)
	public Response indexNews( @ApiParam(value = "页码从1开始", required = true ) @PathVariable Integer page){
		return new ListResponse<NewsListDto>(convertToNewsListDto( newsService.findAll(page, null))); 
	}
	
	@ApiOperation(value = "获取某个具体分类下的新闻列表",notes="获取某个具体分类下的新闻列表", response=NewsListDto.class)
	@RequestMapping(value = "/news/{categoryid}/{page}", method = RequestMethod.GET)
	public Response news(@ApiParam(value = "新闻分类id", required = true ) @PathVariable Long categoryid,
			@ApiParam(value = "页码，从1开始", required = true ) @PathVariable Integer page){
		return new ListResponse<NewsListDto>(convertToNewsListDto(newsService.findAll(page, categoryid)));
	}
	
	@ApiOperation(value = "获取所有新闻分类",notes="获取所有新闻分类", response=NewsCategory.class)
	@RequestMapping(value = "/categorys", method = RequestMethod.GET)
	public Response categorys(){
		return new ListResponse<NewsCategory> (newsService.findAllCategory());
	}
	
	@ApiOperation(value = "获取新闻详情",notes="获取新闻详情", response=News.class)
	@RequestMapping(value = "/newsdetail/{id}", method = RequestMethod.GET)
	public Response newsdetail(@ApiParam(value = "新闻id", required = true ) @PathVariable Long id){
		return new ObjectResponse<News>(newsService.find(id));
	}
	
	@ApiOperation(value = "提交评论",notes="成功返回success", response=Response.class)
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public Response addComment(@ApiParam(value = "新闻id", required = true )  Long newsid,
			@ApiParam(value = "评论内容", required = true )  String body,
			@ApiParam(value = "token", required = true )  String token){
		News news = this.newsService.find(newsid);
		User user = (User)redisTemplate.opsForValue().get(token);
		if(user==null)
			return  new FailedResponse("token无效");
		if(news==null)
			return  new FailedResponse("被评论新闻不存在");
		
		Comment comment = new Comment();
		comment.setBody(body);
		comment.setNews(news);
		comment.setUser(user);
		newsService.saveComment(comment);
		return new SuccessResponse("评论成功！");
	}
	
	
	private List<NewsListDto> convertToNewsListDto(List<News> newslist){
		List<NewsListDto> dtos = new ArrayList<NewsListDto>();
		if(!CollectionUtils.isEmpty(newslist)){
			for(News news:newslist){
				dtos.add(new NewsListDto(news));
			}
		}
		return dtos;
	}
	
}
