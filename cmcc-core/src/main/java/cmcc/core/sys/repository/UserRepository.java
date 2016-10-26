package cmcc.core.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cmcc.common.repository.SimpleCurdRepository;
import cmcc.core.sys.entity.User;


public interface UserRepository   extends SimpleCurdRepository<User ,Long>{
	
	public User findByUsername(String username);
	
	public User findByUsernameAndPassword(String username,String password);
	@Query("select b from User b  inner join fetch b.roles as a where a.id=?1 ")
	public List<User> findUserByRole(Long rolecode);
	
	
}
