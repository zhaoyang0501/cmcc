package cmcc.admin.web.sys;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.admin.web.sys.dto.DeptmentTree;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.entity.Deptment;
import cmcc.core.entity.Role;
import cmcc.core.entity.User;
import cmcc.core.service.sys.UserService;
import cmcc.core.sys.service.DeptmentService;



@Controller
@RequestMapping("sys/user")
public class UserController extends AbstractBaseCURDController<User,Long>  {
	
	@Autowired
	private DeptmentService deptmentService;
	
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

	@RequestMapping("freeze")
	@ResponseBody
	public Response delete(Long id) {
		User user = getSimpleCurdService().find(id);
		user.setIsFreeze(true);
		getSimpleCurdService().save(user);
		return new SuccessResponse("冻结成功！");
	}
	
	@RequestMapping("unfreeze")
	@ResponseBody
	public Response unfreeze(Long id) {
		User user = getSimpleCurdService().find(id);
		user.setIsFreeze(false);
		getSimpleCurdService().save(user);
		return new SuccessResponse("解冻成功！");
	}
	
	@RequestMapping("alldeptments")
	@ResponseBody
	public List<DeptmentTree> allDeptments(){
		List<Deptment> deptements = deptmentService.findAll();
		List<DeptmentTree> deptmentTrees = new ArrayList<DeptmentTree>();
		for(Deptment dept:deptements){
			deptmentTrees.add(new DeptmentTree(dept));
		}
		return deptmentTrees;
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
