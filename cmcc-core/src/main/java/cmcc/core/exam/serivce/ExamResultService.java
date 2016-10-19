package cmcc.core.exam.serivce;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
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
		return true;
	}
	
}
