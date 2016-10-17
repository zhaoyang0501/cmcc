package cmcc.api.web.exam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cmcc.api.web.exam.dto.ExamDto;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.core.bbs.entity.Article;
import cmcc.core.exam.entity.Exam;
import cmcc.core.exam.serivce.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "趣味答题")
@RestController
public class ExamController {
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private ExamService examService;
	
	@ApiOperation(value = "获取所有有效试卷",notes="成功返回试卷数组列表", response=ExamDto.class)
	@RequestMapping(value = "/allexams", method = RequestMethod.GET)
	public Response allexams(){
		List<Exam> exams = examService.findEnableExam();
		List<ExamDto> examdtos = new ArrayList<ExamDto>();
		for(Exam exam:exams){
			examdtos.add(new ExamDto(exam));
		}
		return new ListResponse<ExamDto>(examdtos);
	}
	@ApiOperation(value = "获取试卷的详情",notes="成功返回试卷详细、题目列表等", response=Exam.class)
	@RequestMapping(value = "/exam/{id}", method = RequestMethod.GET)
	public Response exam(@PathVariable Long id){
		Exam exam = examService.find(id);
		return new ObjectResponse<Exam>(exam);
	}
	
	
}
