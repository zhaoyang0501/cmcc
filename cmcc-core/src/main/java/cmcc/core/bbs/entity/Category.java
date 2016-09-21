package cmcc.core.bbs.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cmcc.common.entity.BaseEntity;

@Entity
@Table(name = "t_bbs_category")
public class Category extends BaseEntity<Long>{
	private String  name;
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