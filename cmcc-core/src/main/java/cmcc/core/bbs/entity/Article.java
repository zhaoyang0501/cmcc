package cmcc.core.bbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.sys.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Table(name = "t_bbs_article")
public class Article extends BaseEntity<Long>{
	
	@ApiModelProperty(value="帖子名称")
	private String  title;
	
	@ApiModelProperty(value="帖子内容")
	private String body;
	
	@ApiModelProperty(value="点赞")
	@Column(name="_like")
	private Integer like;
	
	@ApiModelProperty(value="点击量")
	private Integer click;
	
	@ApiModelProperty(value="板块")
	@OneToOne
	private BbsCategory category;
	
	@ApiModelProperty(value="发表人")
	@OneToOne
	private User user;

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

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public BbsCategory getCategory() {
		return category;
	}

	public void setCategory(BbsCategory category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
