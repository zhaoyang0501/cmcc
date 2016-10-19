package cmcc.api.web;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.exception.AlreadyExistedException;
import cmcc.common.util.UuidGenerater;
import cmcc.core.entity.User;
import cmcc.core.service.sys.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "首页")
@RestController
public class IndexController {
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "用户注册", notes = "成功返回success")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response login(@ApiParam(value = "用户名(手机号码)", required = true)@RequestParam String username,
			@ApiParam(value = "密码", required = true) @RequestParam String password,
			@ApiParam(value = "姓名", required = false)@RequestParam  String chinesename) {
		try {
			userService.registerUser(username, password, chinesename);
		} catch (AlreadyExistedException e) {
			return new FailedResponse("该手机号码已经存在！");
		}
		return new SuccessResponse();
	}

	@ApiOperation(value = "用户登录", notes = "成功返回用户token")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(
			@ApiParam(value = "用户名(手机号码)", required = true) @RequestParam String username, 
			@ApiParam(value = "用户密码", required = true)  @RequestParam  String password) {
		User user = this.userService.login(username, password);
		if(user!=null){
			String token =UuidGenerater.createUuid();
			redisTemplate.opsForValue().set(token,user);	
			redisTemplate.expire(token, 1, TimeUnit.HOURS);
			return new ObjectResponse<String>(token);
		}else{
			return new FailedResponse("用户名密码不正确");
		}
		
	}

	@ApiOperation(value = "用户退出", notes = "成功返回success")
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	public Response	loginout(@ApiParam(value = "token", required = true) @RequestParam String token) {
		redisTemplate.delete(token);
		return new SuccessResponse();
	}
	
	@ApiOperation(value = "密码重置", notes = "需要token，成功返回success")
	@RequestMapping(value = "/resetpw", method = RequestMethod.POST)
	public Response	resetpw(@ApiParam(value = "token", required = true) @RequestParam String token,
			@ApiParam(value = "新密码", required = true) String password,
			@ApiParam(value = "密码重复", required = true) String passwordagain) {
		if(!isPasswordEnable(password,passwordagain))
			return new FailedResponse("两次密码不一致");
		
		User user = (User)redisTemplate.opsForValue().get(token);
		if(user==null)
			return new FailedResponse("token无效");
		else{
			user.setPassword(DigestUtils.md5Hex(password));
			userService.save(user);
		}
		
		return new SuccessResponse();
	}
	
	public Boolean isPasswordEnable(String password,String passwordagain){
		if(password==null||passwordagain==null)
			return false;
		return password.equals(passwordagain);
	}
}
