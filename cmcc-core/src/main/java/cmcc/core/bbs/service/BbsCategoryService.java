package cmcc.core.bbs.service;
import java.util.List;

import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.bbs.entity.Category;

@Service
public class BbsCategoryService extends SimpleCurdService<Category, Long> {
	
	public List<Category> findAll(){
		return (List<Category>) this.simpleCurdRepository.findAll();
	}
}
