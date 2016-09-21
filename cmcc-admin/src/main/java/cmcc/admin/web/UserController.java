package cmcc.admin.web;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.entity.Role;
import cmcc.core.entity.User;
import cmcc.core.service.sys.UserService;



@Controller
@RequestMapping("sys/user")
public class UserController extends AbstractBaseCURDController<User,Long>  {
	
	@Override
	public UserService getSimpleCurdService() {
		return (UserService)super.getSimpleCurdService();
	}
	
	@Override
	public String getBasePath() {
		return "sys/user";
	}
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("roles", this.getSimpleCurdService().findAllRoles());
		return this.getBasePath()+"/index";
	}

	@ModelAttribute
	public User preget(@RequestParam(required=false) Long id,@RequestParam(required=false) String role) {
		User user = new User();
		if (id!=null){
			user = this.getSimpleCurdService().find(id);
		}else{
			user.setPassword( DigestUtils.md5Hex(User.DEFAULT_PASSWORD));
		}
		if(StringUtils.isNotBlank(role)){
			String[] ids = role.split(",");
			Set<Role> roles = new HashSet<Role>();
			for(int i=0;i<ids.length;i++){
				roles.add(this.getSimpleCurdService().findRole(Long.valueOf(ids[i])));
			}
			user.setRoles(roles);
		}
		return user;
	}
}
