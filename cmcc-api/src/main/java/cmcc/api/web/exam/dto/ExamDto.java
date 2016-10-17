package cmcc.api.web.exam.dto;

import cmcc.core.exam.entity.Exam;
import io.swagger.annotations.ApiModelProperty;

public class ExamDto {
	
	@ApiModelProperty(name="试卷id",value="试卷id")
	private Long id;
	
	@ApiModelProperty(name="试卷标题",value="试卷标题")
	private String title;
	
	@ApiModelProperty(name="考试用时",value="考试用时")
	private Integer minute;
	
	
	@ApiModelProperty(value="问题数量")
	private Integer questions;


	public ExamDto(Exam exam) {
		this.id=exam.getId();
		this.title=exam.getTitle();
		this.minute=exam.getMinute();
		this.questions=exam.getQuestions().size();
	}


	public String getTitle() {
		return title;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getMinute() {
		return minute;
	}


	public void setMinute(Integer minute) {
		this.minute = minute;
	}


	public Integer getQuestions() {
		return questions;
	}


	public void setQuestions(Integer questions) {
		this.questions = questions;
	}
	
}
