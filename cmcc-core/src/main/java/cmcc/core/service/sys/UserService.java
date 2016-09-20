package cmcc.core.service.sys;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.entity.Role;
import cmcc.core.entity.User;
import cmcc.core.repository.RoleRepository;
import cmcc.core.repository.UserRepository;

@Service
public class UserService extends SimpleCurdService<User, Long> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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
