package cmcc.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.exception.AlreadyExistedException;
import cmcc.core.dto.UserDto;
import cmcc.core.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "首页")
@RestController
public class IndexController {
	
	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "用户注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response login(@ApiParam(value = "用户名(手机号码)", required = true) String username,
			@ApiParam(value = "密码", required = true) String password,
			@ApiParam(value = "姓名", required = false) String chinesename) {
		try {
			userService.registerUser(username, password, chinesename);
		} catch (AlreadyExistedException e) {
			return new FailedResponse("该手机号码已经存在！");
		}
		return new SuccessResponse();
	}

	@ApiOperation(value = "用户登录", notes = "返回用户token")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ObjectResponse<UserDto> login(@ApiParam(value = "用户名(手机号码(", required = true) String username, 
			@ApiParam(value = "用户密码", required = true)  String password) {
			return new ObjectResponse<UserDto>(new UserDto());
	}

	@ApiOperation(value = "用户退出", notes = "")
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	public Response	loginout(String token) {
		redisTemplate.opsForValue().set("111","3333333333333");
		return new SuccessResponse();
	}
	
	
}
