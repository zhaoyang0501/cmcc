package cmcc.api.web.center;

import cmcc.core.sys.entity.User;
import io.swagger.annotations.ApiModelProperty;

public class UserInfoDto {
	
	@ApiModelProperty(value="登录令牌")
	private String token;
	
	@ApiModelProperty(value="用户名（139邮箱）")
	private String username;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@ApiModelProperty(value="电话")
	private String tel;
	
	@ApiModelProperty(value="姓名/昵称")
	private String chinesename ="";
	
	@ApiModelProperty(value="性别")
	private String sex;
	
	@ApiModelProperty(value="是否冻结")
	private Boolean isFreeze = false;
	
	@ApiModelProperty(value="是否绑定邮箱")
	private Boolean isBind =false;
	
	@ApiModelProperty(value="头像")
	private String headimg;
	
	@ApiModelProperty(value="工号")
	private String empid;
	
	@ApiModelProperty(value="所属机构名称")
	private String deptName;
	
	@ApiModelProperty(value="积分")
	private Integer score = 0;
	
	@ApiModelProperty(value="等级")
	private String level = "";

	
	public UserInfoDto(User user,String token ){
		this.token=token;
		this.chinesename=user.getChinesename();
		this.deptName=user.getDeptment()==null?"":user.getDeptment().getName();
		this.empid=user.getEmpid();
		this.headimg=user.getHeadimg();
		this.isBind=user.getIsBind()==null?false:user.getIsBind();
		this.isFreeze= user.getIsFreeze();
		this.remark=user.getRemark();
		this.score=user.getScore();
		this.sex=user.getSex();
		this.tel=user.getTel();
		this.username=user.getUsername();
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getChinesename() {
		return chinesename;
	}

	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Boolean getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
