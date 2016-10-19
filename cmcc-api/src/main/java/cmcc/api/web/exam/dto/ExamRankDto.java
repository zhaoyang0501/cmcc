package cmcc.api.web.exam.dto;

import io.swagger.annotations.ApiModelProperty;


public class ExamRankDto {
	@ApiModelProperty(name="用户名",value="用户名")
	private String username;
	
	@ApiModelProperty(name="头像",value="头像")
	private String img;
	
	@ApiModelProperty(name="用户id",value="用户id")
	private Long userid;
	
	@ApiModelProperty(name="积分",value="积分")
	private Integer score;
	
	public ExamRankDto(String username, String img, Long userid, Integer score) {
		super();
		this.username = username;
		this.img = img;
		this.userid = userid;
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
}
