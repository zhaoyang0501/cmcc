package cmcc.core.exam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.sys.entity.User;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "t_exam_examresult")
public class ExamResult extends BaseEntity<Long> {
	
	@ApiModelProperty(name="试卷",value="试卷")
	@ManyToOne
	private Exam exam;

	@ApiModelProperty(name="用户",value="用户")
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	@ApiModelProperty(name="得分",value="得分")
	private Double score;
	
	@ApiModelProperty(name="总题目",value="总题目")
	private Integer totalNum;
	
	@ApiModelProperty(name="答对题数",value="答对题数")
	private Integer rightNum;
	
	@ApiModelProperty(name="用时",value="用时")
	private Integer minute;
	
	@ApiModelProperty(name="答题明细",value="答题明细")
	@OneToMany( mappedBy = "examResult")
	private List<ResultItem> resultItems;

	public Exam getExam() {
		return exam;
	}
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getRightNum() {
		return rightNum;
	}

	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
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
