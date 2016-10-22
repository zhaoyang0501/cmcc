package cmcc.core.news.service;

import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.news.entity.Comment;
@Service
public class NewsCommentService extends SimpleCurdService<Comment, Long> {
	
}
