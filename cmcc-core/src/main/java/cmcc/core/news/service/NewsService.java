package cmcc.core.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.news.entity.News;
import cmcc.core.news.entity.NewsCategory;
import cmcc.core.news.repository.NewsCategoryRespository;
import cmcc.core.news.repository.NewsRespository;
@Service
public class NewsService extends SimpleCurdService<News, Long> {
	
	@Autowired
	private NewsRespository newsRepository;
	
	@Autowired
	private NewsCategoryRespository newsCategoryRespository;
	
	public List<NewsCategory> findAllCategory(){
		return (List<NewsCategory>) this.newsCategoryRespository.findAll();
	}
	
}
