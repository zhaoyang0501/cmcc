package cmcc.admin.web.exam.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cmcc.core.exam.entity.Exam;

public class ExamDto {
	private Long id;
	private String title;
	private Integer qnum;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8") 
	private Date createDate;
	private Boolean isEnable;
	private Integer minute;
	
	public ExamDto(){
		super();
	}
	
	public ExamDto(Exam exam){
		this.id=exam.getId();
		this.isEnable=exam.getIsEnable();
		this.qnum=exam.getQuestions().size();
		this.title=exam.getTitle();
		this.createDate=exam.getCreateDate();
		this.minute=exam.getMinute();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getQnum() {
		return qnum;
	}
	public void setQnum(Integer qnum) {
		this.qnum = qnum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
