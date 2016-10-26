package cmcc.core.sys.service;
import java.util.List;

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
import org.springframework.stereotype.Service;

import cmcc.common.exception.AlreadyExistedException;
import cmcc.common.service.SimpleCurdService;
import cmcc.core.sys.entity.Role;
import cmcc.core.sys.entity.User;
import cmcc.core.sys.repository.RoleRepository;
import cmcc.core.sys.repository.UserRepository;

@Service
public class UserService extends SimpleCurdService<User, Long> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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
