package cmcc.core.bbs.service;
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
import cmcc.core.bbs.entity.Article;
import cmcc.core.bbs.repository.ArticleRepository;
import cmcc.core.bbs.repository.CommentRepository;

@Service
public class ArticleService extends SimpleCurdService<Article, Long> {
	
	@Autowired
	public ArticleRepository articleRepository;
	
	@Autowired
	public CommentRepository commentRepository;
	
	public void deleteComment(Long id){
		this.commentRepository.delete(id);
	}
	
	public Page<Article> findAll(final int pageNumber, final int pageSize,final String title,final Long categoryid){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<Article> spec = new Specification<Article>() {
             public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (StringUtils.isNotBlank(title)) {
                  predicate.getExpressions().add(cb.like(root.get("title").as(String.class), "%"+title+"%"));
             }
             if (categoryid!=null) {
                 predicate.getExpressions().add(cb.equal(root.get("category").get("id").as(Long.class),categoryid));
             }
             return predicate;
             }
        };
        Page<Article> result = (Page<Article>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
  } 
	
}
