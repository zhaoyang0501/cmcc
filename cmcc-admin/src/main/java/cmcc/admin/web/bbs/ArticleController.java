package cmcc.admin.web.bbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.common.dto.json.DataTableResponse;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.bbs.entity.Article;
import cmcc.core.bbs.entity.Comment;
import cmcc.core.bbs.service.ArticleService;
import cmcc.core.bbs.service.BbsCategoryService;
import cmcc.core.sys.entity.User;

@Controller
@RequestMapping("bbs/article")
public class ArticleController extends AbstractBaseCURDController<Article,Long>  {
	
	@Autowired
	private BbsCategoryService bbsCategoryService1;
	
	@Override
	public ArticleService getSimpleCurdService() {
		return (ArticleService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "bbs/article";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys",bbsCategoryService1.findAll());
		return this.getBasePath()+"/index";
	}
	
	/***
	 * 显示所有评论
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="comments/{id}",method=RequestMethod.GET)
	public Response comments(Model model,@PathVariable Long id) {
		return new ListResponse<Comment>(this.getSimpleCurdService().find(id).getComments());
	}
	/***
	 * 删除某条评论
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteComments/{id}",method=RequestMethod.GET)
	public Response deleteComments(Model model,@PathVariable Long id) {
		this.getSimpleCurdService().deleteComment(id);
		return new SuccessResponse("评论删除成功");
	}
	/***
	 * 跳转到修改帖子界面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="goupdate/{id}",method=RequestMethod.GET)
	public String goupdate(Model model,@PathVariable Long id) {
		model.addAttribute("categorys",bbsCategoryService1.findAll());
		model.addAttribute("article",this.getSimpleCurdService().find(id));
		return this.getBasePath()+"/update";
	}
	/***
	 * 确认修改
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="goupdate",method=RequestMethod.POST)
	public String goupdate(Model model,Article article) {
		this.getSimpleCurdService().save(article);
		
		model.addAttribute("response",new SuccessResponse("操作成功"));
		model.addAttribute("categorys",bbsCategoryService1.findAll());
		return this.getBasePath()+"/index";
	}
	
	
	@RequestMapping("listall")
	@ResponseBody
	public Response listall(Integer start, Integer length, String title,Long categoryid) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<Article> m = this.getSimpleCurdService().findAll(pageNumber, pageSize, title,categoryid);
		return new DataTableResponse<Article>( m.getContent(),(int) m.getTotalElements() );
	}

	
	@ModelAttribute
	public Article preget(@RequestParam(required=false) Long id) {
		Article article = new Article();
		if (id!=null){
			article = this.getSimpleCurdService().find(id);
		}
		return article;
	}
}
