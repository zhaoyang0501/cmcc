package cmcc.api.web.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cmcc.api.token.TokenValid;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.core.bbs.entity.Article;
import cmcc.core.bbs.entity.BbsCategory;
import cmcc.core.bbs.service.ArticleService;
import cmcc.core.bbs.service.BbsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "论坛")
@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private BbsCategoryService bbsCategoryService;
	
	@TokenValid
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
	
	@ApiOperation(value = "发帖")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Response createArticle(@ApiParam(value = "标题", required = true) String title,
			@ApiParam(value = "内容", required = true) String body){
		
		Article article = new Article();
		article.setBody(body);
		article.setTitle(title);
		this.articleService.save(article);
		return new SuccessResponse();
	}
}
