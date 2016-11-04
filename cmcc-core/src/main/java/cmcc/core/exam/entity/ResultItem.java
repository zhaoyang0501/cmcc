package cmcc.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "t_exam_resultitem")
public class ResultItem extends BaseEntity<Long> {
	
	@ApiModelProperty(name="问题",value="问题")
	@OneToOne
	private Question question;
	
	@ApiModelProperty(name="用户提交的答案",value="用户提交的答案")
	private String answer;
	
	@ApiModelProperty(name="是否正确",value="是否正确")
	private Boolean isRight;
	
	@ApiModelProperty(hidden=true)
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
