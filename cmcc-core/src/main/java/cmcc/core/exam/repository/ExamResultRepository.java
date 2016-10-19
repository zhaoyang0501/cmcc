package cmcc.core.exam.repository;
import java.util.Date;
import java.util.List;

import cmcc.common.repository.SimpleCurdRepository;
import cmcc.core.exam.entity.ExamResult;
public interface ExamResultRepository   extends SimpleCurdRepository<ExamResult ,Long>{
	public List<ExamResult> findTop10ByCreateDateBetweenOrderByScore(Date start,Date end);
}
