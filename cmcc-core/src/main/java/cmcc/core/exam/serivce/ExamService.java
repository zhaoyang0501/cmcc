package cmcc.core.exam.serivce;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.exam.entity.Exam;
import cmcc.core.exam.repository.ExamRepository;

@Service
public class ExamService extends SimpleCurdService<Exam, Long> {
	
	@Autowired
	private ExamRepository examRepository;
	
	public List<Exam> findEnableExam(){
		return examRepository.findByIsEnable(true);
	}
}
