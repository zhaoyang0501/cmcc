package cmcc.core.exam.repository;
import java.util.List;

import cmcc.common.repository.SimpleCurdRepository;
import cmcc.core.exam.entity.Exam;
public interface ExamRepository   extends SimpleCurdRepository<Exam ,Long>{
	
	public List<Exam> findByIsEnable(Boolean isEnable);
	
	public List<Exam> findByExamCategoryIdAndIsEnable(Long categoryid,Boolean isEnable);
}
