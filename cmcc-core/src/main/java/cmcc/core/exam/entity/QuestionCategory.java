package cmcc.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cmcc.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "t_exam_question_category")
@JsonIgnoreProperties(value={"createDate","updateDate"})
public class QuestionCategory extends BaseEntity<Long>{
	
	@ApiModelProperty(value="题目分类名称")
	private String  name;
	
	@ApiModelProperty(value="题目分类备注")
	private String remark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
