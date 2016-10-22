package cmcc.admin.web.news;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.news.entity.Comment;
import cmcc.core.news.service.NewsCommentService;

@Controller
@RequestMapping("news/comment")
public class NewsCommentController extends AbstractBaseCURDController<Comment,Long>  {

	@Override
	public NewsCommentService getSimpleCurdService() {
		return (NewsCommentService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "news/comment";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}
	
	
}
