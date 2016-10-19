package cmcc.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;

@Entity
@Table(name = "t_exam_resultitem")
public class ResultItem extends BaseEntity<Long> {
	
	@OneToOne
	private Question question;
	
	private String answer;
	
	private Boolean isRight;
	
	@OneToOne
	private ExamResult examResult;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public ExamResult getExamResult() {
		return examResult;
	}
	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Boolean getIsRight() {
		return isRight;
	}
	public void setIsRight(Boolean isRight) {
		this.isRight = isRight;
	}
	
}
