package cmcc.api.web.exam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cmcc.api.web.exam.dto.ExamAnswer;
import cmcc.api.web.exam.dto.ExamDto;
import cmcc.api.web.exam.dto.ExamRankDto;
import cmcc.api.web.exam.dto.Submit;
import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ListResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.util.StringUtil;
import cmcc.core.exam.entity.Exam;
import cmcc.core.exam.entity.ExamResult;
import cmcc.core.exam.entity.Question;
import cmcc.core.exam.entity.ResultItem;
import cmcc.core.exam.serivce.ExamResultService;
import cmcc.core.exam.serivce.ExamService;
import cmcc.core.sys.entity.User;

@Api(value = "趣味答题")
@RestController
@RequestMapping("exam")
public class ExamController {
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamResultService examResultService;
	
	@ApiOperation(value = "获取所有有效试卷",notes="成功返回试卷数组列表", response=ExamDto.class)
	@RequestMapping(value = "/exams/enable", method = RequestMethod.GET)
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
	@ApiOperation(value = "提交考试结果",notes="成功返回success")
	@RequestMapping(value = "/submitexam", method = RequestMethod.POST)
	public Response submitExam(  @ApiParam(value = "提交结果", required = true ) @RequestBody   Submit submit,
			@ApiParam(value = "token", required = true) @RequestParam String token){
		User user = (User)redisTemplate.opsForValue().get(token);
		if(user==null)
			return new FailedResponse("token无效");
		else{
			/**从dto转化成examresult*/
			Exam exam = examService.find(submit.getExamid());
			List<Question> questions = exam.getQuestions();
			ExamResult examResult = new ExamResult();
			List<ResultItem> items = new ArrayList<ResultItem>();
			for(Question q:questions){
				ResultItem item = new ResultItem();
				item.setQuestion(q);
				String answer = getAnswersFromSubmit(submit,q.getId());
				if(answer==null)
					return  new FailedResponse("答案参数非法");
				item.setAnswer(answer);
				item.setIsRight(examResultService.isRight(answer, q.getAnswers()));
				items.add(item);
			}
			examResult.setResultItems(items);
			examResult.setUser(user);
			examResult.setCreateDate(new Date());
			examResult.setMinute(submit.getMinute());
			examResultService.save(examResult);
			return new ObjectResponse<ExamResult>(examResult);
		}
	}
	
	@ApiOperation(value = "获取今日考试提交排行榜",notes="成功返回今日考试考试排行列表", response=ExamRankDto.class)
	@RequestMapping(value = "/ranklist/day", method = RequestMethod.GET)
	public Response dayRanklist(){
		List<ExamRankDto> dtos = new ArrayList<ExamRankDto>();
		dtos.add(new ExamRankDto("潘朝阳","",1l,80));
		dtos.add(new ExamRankDto("习近平","",2l,40));
		return new ListResponse<ExamRankDto>(dtos) ;
	}
	/**TODO 改成*/
	@ApiOperation(value = "获取月度考试提交排行榜",notes="成功返回当月考试考试排行列表", response=ExamRankDto.class)
	@RequestMapping(value = "/ranklist/month", method = RequestMethod.GET)
	public Response monthRanklist(){
		List<ExamRankDto> dtos = new ArrayList<ExamRankDto>();
		dtos.add(new ExamRankDto("潘朝阳","",1l,80));
		dtos.add(new ExamRankDto("习近平","",2l,40));
		return new ListResponse<ExamRankDto>(dtos) ;
	}
	
	private String getAnswersFromSubmit(Submit submit,Long qid){
		if(submit!=null&&submit.getAnswers()!=null){
			for(ExamAnswer a:submit.getAnswers()){
				if(a.qid.equals(qid)){
					Arrays.sort(a.aids);
					return StringUtil.toString(a.aids);
				}
			}
		}
		return null;
	}
	
	
}
