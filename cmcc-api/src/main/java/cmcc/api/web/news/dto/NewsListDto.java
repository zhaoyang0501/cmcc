package cmcc.api.web.news.dto;

import java.util.Date;

import cmcc.core.news.entity.News;
import cmcc.core.news.entity.NewsCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class NewsListDto {
	
	@ApiModelProperty(value="新闻id",notes="新闻id")
	public Long id;
	
	@ApiModelProperty(value="新闻标题",notes="新闻标题")
	public String title;
	
	@ApiModelProperty(value="新闻内容",notes="新闻内容")
	public String body;
	
	@ApiModelProperty(value="图片",notes="图片")
	public String img;
	
	@ApiModelProperty(value="创建日期",notes="创建日期")
	public Date createDate;
	
	@ApiModelProperty(value="分类",notes="分类")
	public NewsCategory category;

	public NewsListDto(News news) {
		super();
		this.body=news.getBody();
		this.title=news.getTitle();
		this.id=news.getId();
		this.img="";
		this.category=news.getCategory();
		this.createDate=news.getCreateDate();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public NewsCategory getCategory() {
		return category;
	}

	public void setCategory(NewsCategory category) {
		this.category = category;
	}
	
	
}
