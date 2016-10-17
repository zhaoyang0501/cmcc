package cmcc.core.exam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cmcc.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Entity
@Table(name = "t_exam_exam")
@JsonIgnoreProperties(value={"createDate","updateDate"})
public class Exam extends BaseEntity<Long> {
	
	@ApiModelProperty(name="试卷标题",value="试卷标题")
	private String title;
	
	@ApiModelProperty(hidden=true)
	private Date startDate;
	
	@ApiModelProperty(hidden=true)
	private Date endDate;
	
	@ApiModelProperty(name="考试用时",value="考试用时")
	private Integer minute;
	
	@ApiModelProperty(name="有效",value="有效")
	private Boolean isEnable ;
	
	@ApiModelProperty(name="题目",value="题目")
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,  
	            CascadeType.REMOVE }, fetch = FetchType.LAZY)  
	private List<Question> questions ;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
}
