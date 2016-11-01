package cmcc.core.sys.service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cmcc.common.exception.AlreadyExistedException;
import cmcc.common.service.SimpleCurdService;
import cmcc.common.util.MailSenderUtil;
import cmcc.common.util.MailSenderUtilImpl;
import cmcc.core.sys.entity.Role;
import cmcc.core.sys.entity.User;
import cmcc.core.sys.repository.RoleRepository;
import cmcc.core.sys.repository.UserRepository;

@Service
public class UserService extends SimpleCurdService<User, Long> {
	
	public final static String BIND_MAIL_SEND = "BIND_MAIL_SEND";
	
	public final static String BIND_MAIL_CODE = "BIND_MAIL_CODE";
	
	
	public final static String FORGET_SEND = "FORGET_SEND";
	
	public final static String FORGET_CODE = "FORGET_CODE";
	
	
	public final static String CODE_SUBJECT = "您在培训系统中绑定手机的验证码";
	public final static String FORGET_SUBJECT = "您在培训系统中找回密码的验证码";
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private MailSenderUtil mailSenderUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User getUserByToken(String token){
		User user = (User)redisTemplate.opsForValue().get(token);
		return user;
	}
	
	@Transactional
	public User registerUser(String username,String password,String chinesename) throws AlreadyExistedException{
		User user = userRepository.findByUsername(username);
		if(user!=null)
			throw new AlreadyExistedException("用户名已经存在");
		else
		user = new User();
		user.setChinesename(chinesename);
		user.setUsername(username);
		user.setPassword(DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
		return userRepository.save(user);
	}
	
	public Page<User> findAll(final int pageNumber, final int pageSize,final String name,final Long deptid,final Boolean isFreeze){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
        Specification<User> spec = new Specification<User>() {
             public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (StringUtils.isNotBlank(name)) {
                  predicate.getExpressions().add(cb.like(root.get("chinesename").as(String.class), "%"+name+"%"));
             }
             if (deptid!=null) {
                 predicate.getExpressions().add(cb.equal(root.get("deptment").get("id").as(Long.class),deptid));
             }
             if (isFreeze!=null) {
                 predicate.getExpressions().add(cb.equal(root.get("isFreeze").as(Boolean.class),isFreeze));
             }
             return predicate;
             }
        };
        Page<User> result = (Page<User>) simpleCurdRepository.findAll(spec, pageRequest);
        return result;
   } 
	
	public void forgetSendCode(User user){
		String sendkey = user.getId()+"_"+FORGET_SEND;
		String codekey = user.getId()+"_"+FORGET_CODE;
		if(redisTemplate.opsForValue().get(sendkey)!=null){
			throw new RuntimeException("请一分钟后再试");
		}else{
			redisTemplate.opsForValue().set(sendkey, new Date());
			redisTemplate.expire(sendkey, 1, TimeUnit.MINUTES);
			
			int code = (int)(Math.random()*9000)+1000;
			
			this.mailSenderUtil.sendMail(user.getUsername()+"@139.com", CODE_SUBJECT, "您找回密码的的验证码是："+code+"1小时后过期");
			
			redisTemplate.opsForValue().set(codekey, String.valueOf(code));
			redisTemplate.expire(codekey, 1, TimeUnit.HOURS);
		}
	}
	
	public void BindMailSendCode(User user,String mail){
		String sendkey = user.getId()+"_"+BIND_MAIL_SEND;
		String codekey = user.getId()+"_"+BIND_MAIL_CODE;
		if(redisTemplate.opsForValue().get(sendkey)!=null){
			throw new RuntimeException("请一分钟后再试");
		}else{
			redisTemplate.opsForValue().set(sendkey, new Date());
			redisTemplate.expire(sendkey, 1, TimeUnit.MINUTES);
			
			int code = (int)(Math.random()*9000)+1000;
			
			this.mailSenderUtil.sendMail(mail, CODE_SUBJECT, "您绑定手机的验证码是："+code+"1小时后过期");
			
			redisTemplate.opsForValue().set(codekey, String.valueOf(code));
			redisTemplate.expire(codekey, 1, TimeUnit.HOURS);
		}
	}
	
	public Boolean isForgetCodeSucess(User user,String code){
		String codekey = user.getId()+"_"+FORGET_CODE;
		String codeInRedis = (String)redisTemplate.opsForValue().get(codekey);
		
		if(codeInRedis!=null&&codeInRedis.equals(code)){
			redisTemplate.delete(codekey);
			return true;
		}else
			return false;
	}
	
	public Boolean isCodeSucess(User user,String code){
		String codekey = user.getId()+"_"+BIND_MAIL_CODE;
		String codeInRedis = (String)redisTemplate.opsForValue().get(codekey);
		
		if(codeInRedis!=null&&codeInRedis.equals(code)){
			redisTemplate.delete(codekey);
			return true;
		}else
			return false;
	}
	/***
	 * 登录不成功返回null
	 * @return
	 */
	public User login(String username,String password){
		return  this.userRepository.findByUsernameAndPassword(username, DigestUtils.md5Hex(password));
	}
	
	public User findByUsername(String username){
		return this.userRepository.findByUsername(username);
	}
	
	public List<Role> findAllRoles(){
		return (List<Role>)this.roleRepository.findAll();
	}
	
	public Role findRole(Long id){
		return this.roleRepository.findOne(id);
	}
	
	public List<User> findAll(){
		return (List<User>)this.userRepository.findAll();
	}
	
	public List<User> findUserByRole(Long roleid){
		return this.userRepository.findUserByRole(roleid);
	}
}
