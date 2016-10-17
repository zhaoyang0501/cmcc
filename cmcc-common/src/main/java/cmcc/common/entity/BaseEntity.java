package cmcc.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.swagger.annotations.ApiModelProperty;


@MappedSuperclass
public class BaseEntity<ID extends Serializable>  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private ID id;
	
    @ApiModelProperty(hidden=true)
    private Date createDate = new Date();
	
    @ApiModelProperty(hidden=true)
    private Date updateDate;
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
		
}
