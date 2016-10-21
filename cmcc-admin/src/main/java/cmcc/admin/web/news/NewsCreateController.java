package cmcc.admin.web.news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cmcc.common.dto.json.SuccessResponse;
import cmcc.core.news.entity.News;
import cmcc.core.news.service.NewsService;

@Controller
@RequestMapping("news/create")
public class NewsCreateController   {
	
	@Autowired
	public NewsService newsService;
	
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("categorys",newsService.findAllCategory());
		return "news/create/index";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public String create(Model model,News news) {
		model.addAttribute("response",new SuccessResponse("操作成功！"));
		model.addAttribute("categorys",newsService.findAllCategory());
		this.newsService.save(news);
		return "news/create/index";
	}
}
