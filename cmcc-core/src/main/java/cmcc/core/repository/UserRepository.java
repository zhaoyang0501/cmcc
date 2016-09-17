package cmcc.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cmcc.common.repository.SimpleCurdRepository;
import cmcc.core.entity.User;


public interface UserRepository   extends SimpleCurdRepository<User ,Long>{
	
	public User findByUsername(String username);
	
	@Query("select b from User b  inner join fetch b.roles as a where a.id=?1 ")
	public List<User> findUserByRole(Long rolecode);
}
