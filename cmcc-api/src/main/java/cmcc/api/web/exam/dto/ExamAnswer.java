package cmcc.api.web.exam.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="答案结果集")
public class ExamAnswer {
	
	@ApiModelProperty(name="问题id",value="问题id")
	public Long qid;
	
	@ApiModelProperty(name="答案id",value="答案id")
	public Long[] aids;
	
	public Long getQid() {
		return qid;
	}
	public void setQid(Long qid) {
		this.qid = qid;
	}
	public Long[] getAids() {
		return aids;
	}
	public void setAids(Long[] aids) {
		this.aids = aids;
	}
	
	
}
