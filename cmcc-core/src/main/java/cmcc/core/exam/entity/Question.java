package cmcc.core.exam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;
import cmcc.core.exam.enums.LevelEnum;
import cmcc.core.exam.enums.QuestionTypeEnum;
import io.swagger.annotations.ApiModelProperty;
@Entity
@Table(name = "t_exam_question")
public class Question  extends BaseEntity<Long>{
	
	/**题干*/
	@ApiModelProperty(value="题干")
	private String title;
	
	/**难度*/
	@Enumerated(value = EnumType.STRING)
	private LevelEnum Level = LevelEnum.LEVEL1;
	
	/**题型*/
	@ApiModelProperty(value="题型")
	@Enumerated(value = EnumType.STRING)
	private QuestionTypeEnum type = QuestionTypeEnum.SINGLECHOICE;
	
	/**答案*/
	@ApiModelProperty(value="答案选项")
	 @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,  
	            CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "question")  
	private List<Answer> answers ;
	
	@ManyToOne
	@ApiModelProperty(value="题目分类")
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ApiModelProperty(value="难度")
	public String getLevel() {
		return Level.getLable();
	}

	public void setLevel(LevelEnum level) {
		Level = level;
	}

	public String getType() {
		return type.getLable();
	}

	public void setType(QuestionTypeEnum type) {
		this.type = type;
	}
	
	
	
}
