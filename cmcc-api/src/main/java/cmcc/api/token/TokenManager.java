package cmcc.api.token;

import cmcc.core.sys.entity.User;

public interface TokenManager {
	/***
	 * 校验token是否有效
	 * @param token
	 * @return
	 */
	public User  verifyToken(String token);
	/***
	 * 创建token
	 * @param user
	 * @return token字符串
	 */
	public String createToken(User user);
}
