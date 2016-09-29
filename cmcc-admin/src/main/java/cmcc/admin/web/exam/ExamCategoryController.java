package cmcc.admin.web.exam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.exam.entity.Category;

@Controller
@RequestMapping("exam/category")
public class ExamCategoryController extends AbstractBaseCURDController<Category,Long>  {
	
	@Override
	public String getBasePath() {
		return "exam/category";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}
	
}
