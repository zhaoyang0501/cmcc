package cmcc.core.news.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.news.entity.Comment;
import cmcc.core.news.entity.News;
import cmcc.core.news.entity.NewsCategory;
import cmcc.core.news.repository.NewsCategoryRespository;
import cmcc.core.news.repository.NewsCommentRespository;
import cmcc.core.news.repository.NewsRespository;
@Service
public class NewsService extends SimpleCurdService<News, Long> {
	public static final int PAGE_SIZE=10;
	@Autowired
	private NewsRespository newsRepository;
	
	@Autowired
	private NewsCategoryRespository newsCategoryRespository;
	
	@Autowired
	private NewsCommentRespository newsCommentRespository;
	
	public List<NewsCategory> findAllCategory(){
		return (List<NewsCategory>) this.newsCategoryRespository.findAll();
	}
	
	public void saveComment(Comment comment){
		this.newsCommentRespository.save(comment);
	}
	
	/***
	 * 
	 * @param pageNumber start form 1
	 * @param categoryid
	 * @return
	 */
	public List<News> findAll(final int pageNumber,final Long  categoryid){
		
		if(pageNumber<1)
			throw new IllegalArgumentException("页码不能小于1");
		
        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(Direction.DESC, "createDate"));
        Specification<News> spec = new Specification<News>() {
             public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (categoryid!=null) {
                  predicate.getExpressions().add(cb.equal(root.get("category").get("id").as(Long.class), categoryid));
             }
             return predicate;
             }
        };
        return  simpleCurdRepository.findAll(spec, pageRequest).getContent();
       
  }  
	
}
