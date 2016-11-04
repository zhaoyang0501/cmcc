package cmcc.core.exam.serivce;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cmcc.common.service.SimpleCurdService;
import cmcc.common.util.StringUtil;
import cmcc.core.exam.entity.Answer;
import cmcc.core.exam.entity.ExamResult;
import cmcc.core.exam.repository.ExamResultRepository;

@Service
public class ExamResultService extends SimpleCurdService<ExamResult, Long> {
	
	@Autowired
	private ExamResultRepository examResultRepository;
	
	public List<ExamResult> dayRankList(){
		return this.examResultRepository.findTop10ByCreateDateBetweenOrderByScore(new Date(), new Date());
	}
	
	public List<ExamResult> monthRankList(){
		return this.examResultRepository.findTop10ByCreateDateBetweenOrderByScore(new Date(), new Date());
	}
	
	public Boolean isRight(String answerids,List<Answer> answers){
		List<Long> rightids = new ArrayList<Long>();
		for(Answer answer: answers){
			if(answer.getIsRight())
				rightids.add(answer.getId());
		}
		Collections.sort(rightids);
	
		return answerids.equals(StringUtil.toString(rightids.toArray()));
	}
	
	public static void main(String arg[]){
		List<Long> rightids = new ArrayList<Long>();
		rightids.add(13l);
		rightids.add(4l);
		rightids.add(3l);
		rightids.add(6l);
		Collections.sort(rightids);
		System.out.println(StringUtil.toString(rightids.toArray()));
	}
}
