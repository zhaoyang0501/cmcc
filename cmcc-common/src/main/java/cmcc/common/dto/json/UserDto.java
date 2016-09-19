package cmcc.common.dto.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class UserDto {
	
	@ApiModelProperty(name="登录用户名33")
	private String id;
	
	@ApiModelProperty(name="登录用户名")
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@ApiModelProperty(name="登录用户名")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
