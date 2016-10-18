package cmcc.core.exam.serivce;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
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

	public Page<Exam> findAll(int pageNumber, int pageSize, final Boolean isEnable,final String title) {
		 PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
         Specification<Exam> spec = new Specification<Exam>() {
              public Predicate toPredicate(Root<Exam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Predicate predicate = cb.conjunction();
              if (StringUtils.isNotBlank(title)) {
                   predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+title+"%"));
              }
              if (isEnable!=null) {
                  predicate.getExpressions().add(cb.equal(root.get("isEnable").as(Boolean.class), isEnable));
              }
              return predicate;
              }
         };
         Page<Exam> result = (Page<Exam>) simpleCurdRepository.findAll(spec, pageRequest);
         return result;
	}
}
