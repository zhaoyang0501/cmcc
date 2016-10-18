package cmcc.admin.web.exam;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.admin.web.exam.dto.ExamDto;
import cmcc.common.dto.json.DataTableResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.exam.entity.Exam;
import cmcc.core.exam.serivce.ExamService;

@Controller
@RequestMapping("exam/exam")
public class ExamController extends AbstractBaseCURDController<Exam,Long>  {

	@Override
	public ExamService getSimpleCurdService() {
		return (ExamService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "exam/exam";
	}
	
	@RequestMapping("listall")
	@ResponseBody
	public Response list(Integer start, Integer length, Boolean isEnable,String title) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<Exam> m = getSimpleCurdService().findAll(pageNumber, pageSize, isEnable,title);
		List<ExamDto> examdto = new ArrayList<ExamDto>();
		for(Exam exam:m.getContent()){
			examdto.add(new ExamDto(exam));
		}
		return new DataTableResponse<ExamDto>( examdto,(int) m.getTotalElements() );
	}
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}
	
	
}
