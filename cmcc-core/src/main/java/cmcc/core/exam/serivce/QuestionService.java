package cmcc.core.exam.serivce;
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
import cmcc.core.exam.entity.Category;
import cmcc.core.exam.entity.Question;
import cmcc.core.exam.repository.QuestionRepository;

@Service
public class QuestionService extends SimpleCurdService<Question, Long> {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public Page<Question> findAll(final int pageNumber, final int pageSize,final String name,final Category category){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<Question> spec = new Specification<Question>() {
             public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (StringUtils.isNotBlank(name)) {
                  predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+name+"%"));
             }
             if (category!=null) {
                 predicate.getExpressions().add(cb.equal(root.get("category").as(Category.class),category));
            }
             return predicate;
             }
        };
        Page<Question> result = (Page<Question>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
  } 
	
	
}
