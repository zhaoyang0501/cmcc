package cmcc.core.news.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cmcc.common.entity.BaseEntity;
import cmcc.core.sys.entity.User;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "t_news_comment")
public class Comment extends BaseEntity<Long>{
	
	@ApiModelProperty(value="评论内容")
	private String body;
	
	@ApiModelProperty(value="评论者")
	@OneToOne
	private User user;
	
	@ApiModelProperty(hidden=true)
	@JsonIgnore
	@OneToOne
	private News news;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	
	
	
}
