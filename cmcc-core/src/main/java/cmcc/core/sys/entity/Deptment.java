package cmcc.core.sys.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cmcc.common.entity.BaseEntity;
@Entity
@Table(name = "t_sys_deptment")
public class Deptment extends BaseEntity<Long> {

	private String name;
	
	private String tel;
	
	private String code;
	
	@ManyToOne
    @JoinColumn(name = "parent_id")
	private Deptment parent;
	
	@OneToMany(mappedBy = "parent")
	@JsonIgnore
	private List<Deptment> childrens;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Deptment getParent() {
		return parent;
	}

	public void setParent(Deptment parent) {
		this.parent = parent;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Deptment> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Deptment> childrens) {
		this.childrens = childrens;
	}

	
}
