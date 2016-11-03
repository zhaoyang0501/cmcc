package cmcc.api.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cmcc.api.web.center.UserInfoDto;
import cmcc.common.dto.json.FailedResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.exception.AlreadyExistedException;
import cmcc.common.util.UuidGenerater;
import cmcc.core.sys.entity.User;
import cmcc.core.sys.service.UserService;

@Api(value = "首页-用户中心")
@RestController
public class IndexController {
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value = "获取用户信息并延长token有效期", notes = "成功返回success",response=User.class)
	@RequestMapping(value = "/user/{token}", method = RequestMethod.POST)
	public Response getuser(@ApiParam(value = "token", required = true) @RequestParam String token) {

		User user = userService.getUserByToken(token);
		
		if(user==null){
			return new FailedResponse("token无效");
		}else{
			return new ObjectResponse<User>(user);
		}
	}
	@ApiOperation(value = "用户登录", notes = "成功返回用户token",response=UserInfoDto.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(
			@ApiParam(value = "139邮箱或者工号", required = true) @RequestParam String username, 
			@ApiParam(value = "用户密码", required = true)  @RequestParam  String password) {
		/***TODO 工号登录*/
		User user = this.userService.login(username, password);
		if(user!=null){
			String token =UuidGenerater.createUuid();
			redisTemplate.opsForValue().set(token,user);	
			redisTemplate.expire(token, 1, TimeUnit.HOURS);
			return new ObjectResponse<UserInfoDto>(new UserInfoDto(user,token));
		}else{
			return new FailedResponse("用户名密码不正确");
		}
		
	}
	
	
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


	@ApiOperation(value = "用户退出", notes = "成功返回success")
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	public Response	loginout(@ApiParam(value = "token", required = true) @RequestParam String token) {
		redisTemplate.delete(token);
		return new SuccessResponse();
	}
	
	@ApiOperation(value = "验证绑定139验证验证码是否正确", notes = "成功返回success")
	@RequestMapping(value = "/bindValidate", method = RequestMethod.POST)
	public Response bindValidate(@ApiParam(value = "验证码", required = true) @RequestParam String code,
			@ApiParam(value = "token", required = true) @RequestParam String token){
		
		User user = userService.getUserByToken(token);
		
		if(user==null){
			return new FailedResponse("token无效请登录");
		}else{
			if(userService.isCodeSucess(user, code)){
				user.setIsBind(true);
				userService.save(user);
				return new SuccessResponse("验证通过");
			}
			else
				return new FailedResponse("验证码无效");
		}
	}
	
	@ApiOperation(value = "绑定手机操作发送验证码邮件通知", notes = "成功返回success")
	@RequestMapping(value = "/bindSendMail", method = RequestMethod.POST)
	public Response bindSendMail(@ApiParam(value = "邮箱地址", required = true) @RequestParam String mail,
			@ApiParam(value = "token", required = true) @RequestParam String token ){
		
		User user = userService.getUserByToken(token);
		
		if(user==null){
			return new FailedResponse("token无效请登录");
		}else{
			try {
				userService.BindMailSendCode(user, mail);
			} catch (Exception e) {
				return new FailedResponse(e.getMessage());
			}
		}
		return new SuccessResponse("邮件发送成功！");
	}
	
	@ApiOperation(value = "忘记密码发送验证码邮件通知", notes = "成功返回success")
	@RequestMapping(value = "/forgetSendMail", method = RequestMethod.POST)
	public Response forgetSendMail(@ApiParam(value = "用户名", required = true) @RequestParam String username){
		
		User user = userService.findByUsername(username);
		
		if(user==null){
			return new FailedResponse("账号不存在");
		}else{
			try {
				userService.forgetSendCode(user);
			} catch (Exception e) {
				return new FailedResponse(e.getMessage());
			}
		}
		return new SuccessResponse("邮件发送成功！");
	}
	
	@ApiOperation(value = "忘记密码验证验证码是否正确", notes = "成功返回token")
	@RequestMapping(value = "/forgetValidate", method = RequestMethod.POST)
	public Response forgetValidate(@ApiParam(value = "验证码", required = true) @RequestParam String code,
			@ApiParam(value = "用户名", required = true) @RequestParam String username){
		
		User user = userService.findByUsername(username);
		
		if(user==null){
			return new FailedResponse("账号不存在");
		}else{
			if(userService.isForgetCodeSucess(user, code)){
				String token =UuidGenerater.createUuid();
				redisTemplate.opsForValue().set(token,user);	
				redisTemplate.expire(token, 1, TimeUnit.HOURS);
				return new ObjectResponse<String>(token);
			}
				return new FailedResponse("验证码无效");
		}
		
		
	}
	@ApiOperation(value = "密码重置", notes = "需要token，成功返回success")
	@RequestMapping(value = "/resetpw", method = RequestMethod.POST)
	public Response	resetpw(@ApiParam(value = "token", required = true) @RequestParam String token,
			@ApiParam(value = "新密码", required = true)  @RequestParam String password,
			@ApiParam(value = "密码重复", required = true) @RequestParam  String passwordagain) {
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
