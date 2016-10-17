package cmcc.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "t_exam_answer")
@JsonIgnoreProperties(value={"createDate","updateDate"})
public class Answer extends BaseEntity<Long> {
	
	@ApiModelProperty(value="选项名称")
	private String title;
	
	@ApiModelProperty(value="是否正确答案")
	private Boolean isRight;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="questionid")  
	private Question question;
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsRight() {
		return isRight;
	}

	public void setIsRight(Boolean isRight) {
		this.isRight = isRight;
	}
	
	
}
