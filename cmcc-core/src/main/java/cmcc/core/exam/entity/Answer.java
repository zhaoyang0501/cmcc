package cmcc.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "t_exam_answer")
public class Answer extends BaseEntity<Long> {
	
	private String title;
	
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
