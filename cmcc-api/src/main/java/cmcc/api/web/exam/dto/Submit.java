package cmcc.api.web.exam.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="考试提交结果")
public class Submit {

	@ApiModelProperty(name="试卷id",value="试卷id")
	public Long examid;
	
	@ApiModelProperty(name="用时",value="用时")
	private Integer minute;
	
	public List<ExamAnswer> answers;
	
	public List<ExamAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<ExamAnswer> answers) {
		this.answers = answers;
	}
	public Long getExamid() {
		return examid;
	}
	public void setExamid(Long examid) {
		this.examid = examid;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	
}
