package cmcc.admin.web.exam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.exam.entity.Question;
import cmcc.core.exam.serivce.QuestionService;

@Controller
@RequestMapping("exam/question")
public class QuestionController extends AbstractBaseCURDController<Question,Long>  {
	
	
	@Override
	public QuestionService getSimpleCurdService() {
		return (QuestionService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "exam/question";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("levels", cmcc.core.exam.enums.LevelEnum.values());
		model.addAttribute("types", cmcc.core.exam.enums.QuestionTypeEnum.values());
		return this.getBasePath()+"/index";
	}

	@ModelAttribute
	public Question preget(@RequestParam(required=false) Long id) {
		Question bean = new Question();
		if (id!=null){
			bean = this.getSimpleCurdService().find(id);
		}
		return bean;
	}
}
