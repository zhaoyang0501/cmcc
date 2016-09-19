package cmcc.api.web;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.dto.json.UserDto;
import cmcc.core.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "首页")
@RestController
public class IndexController {
	
	@ApiOperation(value = "用户注册", notes = "")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response login(@ApiParam(value = "用户名", required = true) String username,
			@ApiParam(value = "密码", required = true) String password,
			@ApiParam(value = "姓名", required = false) String chinesename) {
		return new SuccessResponse();
	}

	@ApiOperation(value = "用户登录", notes = "")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ObjectResponse<User> login(String username, String password) {
		return new ObjectResponse<User>(new User());
	}

	@ApiOperation(value = "用户退出", notes = "")
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	public ObjectResponse<String> loginout(String token) {
		return new ObjectResponse<String>(UUID.randomUUID().toString());
	}
	
	@ApiOperation(value = "test", notes = "")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public UserDto userinfo(String token) {
		return new UserDto();
	}
}
