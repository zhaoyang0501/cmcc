package cmcc.api.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cmcc.common.util.UuidGenerater;
import cmcc.core.entity.User;

@Service
public class TokenManagerImpl implements TokenManager{
	
	@Autowired
    private RedisTemplate<Object,Object> redisTemplate;
	
	@Override
	public User verifyToken(String token) {
		
		return null;
	}

	@Override
	public String createToken(User user) {
		String token = UuidGenerater.createUuid();
		redisTemplate.opsForValue().set( UuidGenerater.createUuid(), user);
		return token;
	}

}
