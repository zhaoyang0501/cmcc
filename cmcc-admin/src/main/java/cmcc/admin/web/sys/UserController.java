package cmcc.admin.web.sys;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.admin.web.sys.dto.DeptmentSelect;
import cmcc.admin.web.sys.dto.DeptmentTree;
import cmcc.common.dto.json.DataTableResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.web.AbstractBaseCURDController;
import cmcc.core.sys.entity.Deptment;
import cmcc.core.sys.entity.Role;
import cmcc.core.sys.entity.User;
import cmcc.core.sys.service.DeptmentService;
import cmcc.core.sys.service.UserService;



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
		List<Deptment> deptments = this.deptmentService.queryRootList();
		List<DeptmentSelect> deptmentselect = new ArrayList<DeptmentSelect>();
		for(Deptment dept:deptments){
			DeptmentSelect.convertToSelectDto(dept,deptmentselect);
		}
		model.addAttribute("deptmentselects",deptmentselect);
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
	
	
	@RequestMapping("listall")
	@ResponseBody
	public Response listall(Integer start, Integer length, String value,Long deptid,@RequestParam( defaultValue="false")  Boolean isFreeze) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<User> m = this.getSimpleCurdService().findAll(pageNumber, pageSize, value,deptid,isFreeze);
		return new DataTableResponse<User>( m.getContent(),(int) m.getTotalElements() );
	}
	
	@ModelAttribute
	public User preget(@RequestParam(required=false) Long id,@RequestParam(required=false) String role) {
		User user = new User();
		if (id!=null){
			user = this.getSimpleCurdService().find(id);
			user.setDeptment(null);
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
