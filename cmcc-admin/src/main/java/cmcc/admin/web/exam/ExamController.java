package cmcc.admin.web.exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.common.dto.json.DataTableResponse;
import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.exam.entity.Answer;
import cmcc.core.exam.entity.Category;
import cmcc.core.exam.entity.Exam;
import cmcc.core.exam.entity.Question;
import cmcc.core.exam.serivce.ExamCategoryService;
import cmcc.core.exam.serivce.ExamService;
import cmcc.core.exam.serivce.QuestionService;

@Controller
@RequestMapping("exam/create")
public class ExamController extends AbstractBaseCURDController<Exam,Long>  {
	
	@Autowired
	private ExamCategoryService examCategoryService;
	
	@Autowired
	private QuestionService questionService;
	
	@Override
	public ExamService getSimpleCurdService() {
		return (ExamService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "exam/create";
	}
	
	
	@Override
	@RequestMapping("save")
	@ResponseBody
	public Response save(Exam m) {
		getSimpleCurdService().save(m);
		return new ObjectResponse<Exam>(m);
	}
	
	
	@RequestMapping("selectQuestion")
	@ResponseBody
	public Response selectQuestion(Long qid,Long eid ) {
		if(eid==null){
			return new FailedResponse("请先保存试卷");
		}
		Exam exam = this.getSimpleCurdService().find(eid);
		
		Question question = this.questionService.find(qid);
		exam.getQuestions().add(question);
		this.simpleCurdService.save(exam);
		return new SuccessResponse("添加成功");
	}
	
	@RequestMapping("unselectQuestion")
	@ResponseBody
	public Response unselectQuestion(Long qid,Long eid ) {
		if(eid==null){
			return new FailedResponse("请先保存试卷");
		}
		Exam exam = this.getSimpleCurdService().find(eid);
		if(exam==null){
			return new FailedResponse("请先保存试卷");
		}
		Question question = this.questionService.find(qid);
		exam.getQuestions().remove(question);
		this.simpleCurdService.save(exam);
		return new SuccessResponse("添加成功");
	}
	
	@RequestMapping("questions")
	@ResponseBody
	public Response questions(Long eid ) {
		Exam exam = this.getSimpleCurdService().find(eid);
		return new ListResponse<Question>(exam.getQuestions());
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("levels", cmcc.core.exam.enums.LevelEnum.values());
		model.addAttribute("categorys",examCategoryService.findAll());
		model.addAttribute("types", cmcc.core.exam.enums.QuestionTypeEnum.values());
		return this.getBasePath()+"/index";
	}
	
	
}
