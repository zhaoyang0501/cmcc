package cmcc.api.web.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.core.bbs.entity.Article;
import cmcc.core.bbs.entity.Category;
import cmcc.core.bbs.service.ArticleService;
import cmcc.core.bbs.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "论坛-电子书")
@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation(value = "获取所有板块")
	@RequestMapping(value = "/allcategory", method = RequestMethod.GET)
	public ListResponse<Category> AllCategory(){
		return new ListResponse<Category>(categoryService.findAll());
	}
	
	@ApiOperation(value = "获取帖子")
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public ObjectResponse<Article> article(	@ApiParam(value = "帖子ID", required = true) @PathVariable Long id ){
		return new ObjectResponse<Article>(articleService.find(id));
	}
	
	@ApiOperation(value = "发帖")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ObjectResponse<Article> article(	@RequestBody Article article ){
		
		return new ObjectResponse<Article>(articleService.find(id));
	}
}
