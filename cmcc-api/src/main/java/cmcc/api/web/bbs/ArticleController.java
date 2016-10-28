package cmcc.api.web.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cmcc.api.web.news.dto.NewsListDto;
import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.core.bbs.entity.Article;
import cmcc.core.bbs.entity.BbsCategory;
import cmcc.core.bbs.entity.Comment;
import cmcc.core.bbs.service.ArticleService;
import cmcc.core.bbs.service.BbsCategoryService;
import cmcc.core.sys.entity.User;
import cmcc.core.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "论坛")
@RestController
@RequestMapping("bbs")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BbsCategoryService bbsCategoryService;
	
	
	@ApiOperation(value = "获取所有板块",notes="成功返回板块列表",response=BbsCategory.class)
	@RequestMapping(value = "/allcategory", method = RequestMethod.GET)
	public ListResponse<BbsCategory> AllCategory(){
		return new ListResponse<BbsCategory>(bbsCategoryService.findAll());
	}
	
	@ApiOperation(value = "获取帖子", response=Article.class)
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public Response article(@ApiParam(value = "帖子ID", required = true) @PathVariable Long id ){
		return new ObjectResponse<Article>(articleService.find(id));
	}
	
	
	@ApiOperation(value = "获取某个板块下的帖子",notes="获取精彩热帖", response=NewsListDto.class)
	@RequestMapping(value = "category/{categoryid}/articles/{page}", method = RequestMethod.GET)
	public Response articles(
			@ApiParam(value = "页码从1开始", required = true ) @PathVariable Integer page,
			@ApiParam(value = "排序方式 1最新， 2最热", required = true )  String order
			){
		//TODO
		return null;
	}
	
	@ApiOperation(value = "发表评论",notes="发表评论", response=Response.class)
	@RequestMapping(value = "addcomment/{articleid}", method = RequestMethod.POST)
	public Response addcomment(
			@ApiParam(value = "内容", required = true) String body,
			@ApiParam(value = "文章id", required = true) @RequestParam  @PathVariable Long articleid,
			@ApiParam(value = "token", required = true) @RequestParam String token
			){
		 User user = userService.getUserByToken(token);
		 Article article = articleService.find(articleid);
		 if(user==null)
			 return new FailedResponse("token 无效");
		
		 if(article==null)
			 return new FailedResponse("文章不存在");
		 
		 Comment comment = new Comment();
		 comment.setArticle(article);
		 comment.setBody(body);
		 comment.setUser(user);
		 articleService.saveComment(comment);
		return new SuccessResponse();
	}
	
	@ApiOperation(value = "获取热帖",notes="获取精彩热帖", response=NewsListDto.class)
	@RequestMapping(value = "/articles/{page}", method = RequestMethod.GET)
	public Response indexNews( @ApiParam(value = "页码从1开始", required = true ) @PathVariable Integer page){
		//TODO
		return null;
	}
	
	
	@ApiOperation(value = "发帖")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Response createArticle(
			@ApiParam(value = "标题", required = true) @RequestParam String title,
			@ApiParam(value = "内容", required = true) String body,
			@ApiParam(value = "分类id", required = true) @RequestParam  Long categoryid,
			@ApiParam(value = "token", required = true) @RequestParam String token){
		BbsCategory category = bbsCategoryService.find(categoryid);
		if(category == null)
			return  new FailedResponse("板块不存在");
		 User user = userService.getUserByToken(token);
		
		 if(user==null)
			 return new FailedResponse("token 无效");
		
		 
		Article article = new Article();
		article.setBody(body);
		article.setTitle(title);
		article.setUser(user);
		this.articleService.save(article);
		return new SuccessResponse();
	}
}
