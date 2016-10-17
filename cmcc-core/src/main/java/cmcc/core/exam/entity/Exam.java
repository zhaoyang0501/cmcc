package cmcc.core.exam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
@Entity
@Table(name = "t_exam_exam")
public class Exam extends BaseEntity<Long> {
	
	private String title;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer minute;
	
	private String isEnable ;
	
	
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
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
}
