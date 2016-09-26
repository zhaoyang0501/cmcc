package cmcc.core.exam.serivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.exam.entity.Question;
import cmcc.core.exam.repository.QuestionRepository;

@Service
public class QuestionService extends SimpleCurdService<Question, Long> {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	
	
}
