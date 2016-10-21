package cmcc.admin.web.news;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.news.entity.News;
import cmcc.core.news.service.NewsService;

@Controller
@RequestMapping("news/news")
public class NewsController extends AbstractBaseCURDController<News,Long>  {

	@Override
	public NewsService getSimpleCurdService() {
		return (NewsService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "news/news";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}
	
	@RequestMapping("update/{id}")
	public String update(Model model,@PathVariable Long id) {
		model.addAttribute("news",getSimpleCurdService().find(id));
		model.addAttribute("categorys",getSimpleCurdService().findAllCategory());
		return "news/create/index";
	}
}
