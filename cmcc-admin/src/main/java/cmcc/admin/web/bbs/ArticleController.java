package cmcc.admin.web.bbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.bbs.entity.Article;
import cmcc.core.bbs.service.ArticleService;
import cmcc.core.bbs.service.CategoryService;

@Controller
@RequestMapping("bbs/article")
public class ArticleController extends AbstractBaseCURDController<Article,Long>  {
	
	@Autowired
	private CategoryService categoryService;
	
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
		model.addAttribute("categorys",categoryService.findAll());
		return this.getBasePath()+"/index";
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
