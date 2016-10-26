package cmcc.core.exam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.sys.entity.User;

@Entity
@Table(name = "t_exam_examresult")
public class ExamResult extends BaseEntity<Long> {
	
	@ManyToOne
	private Exam exam;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	private Double score;
	
	private Integer minute;
	
	@OneToMany( mappedBy = "examResult")
	private List<ResultItem> resultItems;

	public Exam getExam() {
		return exam;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public List<ResultItem> getResultItems() {
		return resultItems;
	}

	public void setResultItems(List<ResultItem> resultItems) {
		this.resultItems = resultItems;
	}
	
	
	
}
