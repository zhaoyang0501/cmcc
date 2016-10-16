package cmcc.admin.web.exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.common.dto.json.DataTableResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.exam.entity.Answer;
import cmcc.core.exam.entity.Category;
import cmcc.core.exam.entity.Question;
import cmcc.core.exam.serivce.ExamCategoryService;
import cmcc.core.exam.serivce.QuestionService;

@Controller
@RequestMapping("exam/question")
public class QuestionController extends AbstractBaseCURDController<Question,Long>  {
	
	@Autowired
	private ExamCategoryService examCategoryService;
	
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
		model.addAttribute("categorys",examCategoryService.findAll());
		model.addAttribute("types", cmcc.core.exam.enums.QuestionTypeEnum.values());
		return this.getBasePath()+"/index";
	}
	
	@Override
	@RequestMapping("save")
	@ResponseBody
	public Response save(Question m) {
		for(Answer answer:m.getAnswers()){
			answer.setQuestion(m);
		}
		m.setCategory(examCategoryService.find(m.getCategory().getId()));
		getSimpleCurdService().save(m);
		return new SuccessResponse();
	}
	
	@RequestMapping("listall")
	@ResponseBody
	public Response listall(Integer start, Integer length, String title, Long categoryid) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Category category = categoryid==null?null:examCategoryService.find(categoryid);
		Page<Question> m = getSimpleCurdService().findAll(pageNumber, pageSize, title,category);
		return new DataTableResponse<Question>( m.getContent(),(int) m.getTotalElements() );
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
