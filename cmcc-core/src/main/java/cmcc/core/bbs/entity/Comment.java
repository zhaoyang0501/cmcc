package cmcc.core.bbs.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.entity.User;

@Entity
@Table(name = "t_bbs_comment")
public class Comment extends BaseEntity<Long>{
	
	private String body;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Article article;

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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	
}
