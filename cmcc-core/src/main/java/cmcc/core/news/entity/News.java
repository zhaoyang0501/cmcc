package cmcc.core.news.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.entity.User;

@Entity
@Table(name = "t_news_news")
public class News extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = -5927031699239008428L;

	private String title;
	
	private String body;
	
	@OneToOne
	private NewsCategory category;
	
	@OneToOne
	private User user;
	
	@OneToMany( mappedBy = "news")
	public List<Comment> comments;
	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public NewsCategory getCategory() {
		return category;
	}

	public void setCategory(NewsCategory category) {
		this.category = category;
	}
	
	
	
}
