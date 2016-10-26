package cmcc.api.token;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import cmcc.core.sys.entity.User;


@Aspect
@Configuration
public class TokenAop {
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Pointcut("execution(* cmcc.api.web..*.*(..))&&" + "@annotation(cmcc.api.token.TokenValid)")
    public void tokenPointCut() {
    }
    
	@Around(value = "tokenPointCut()")
    public Object addTokenToMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String token = request.getParameter("token");
        User user = tokenManager.verifyToken(token);
        Assert.notNull(user, "请重新登录");
        return joinPoint.proceed(args);
    }

}
