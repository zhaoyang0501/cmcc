package cmcc.common.web;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cmcc.common.dto.json.DataTableResponse;
import cmcc.common.dto.json.ObjectResponse;
import cmcc.common.dto.json.Response;
import cmcc.common.dto.json.SuccessResponse;
import cmcc.common.entity.BaseEntity;
import cmcc.common.service.SimpleCurdService;
public abstract  class AbstractBaseCURDController<M extends BaseEntity<?>, ID extends Serializable> implements BaseCURDController<M , ID >{
	
	@Autowired
	protected SimpleCurdService<M, ID> simpleCurdService;
	
	abstract protected String getBasePath();
	
	@Override
	@RequestMapping("index")
	public String index(Model model) {
		return this.getBasePath()+"/index";
	}

	@Override
	@RequestMapping("save")
	@ResponseBody
	public Response save(M m) {
		simpleCurdService.save(m);
		return new SuccessResponse();
	}

	@Override
	@RequestMapping("update")
	@ResponseBody
	public Response update(M m) {
		simpleCurdService.save(m);
		return new SuccessResponse();
	}

	@Override
	@RequestMapping("delete")
	@ResponseBody
	public Response delete(ID id) {
		simpleCurdService.delete(id);
		return new SuccessResponse();
	}
	
	@Override
	@RequestMapping("get")
	@ResponseBody
	public Response get(ID id) {
		return new ObjectResponse<M>(simpleCurdService.find(id));
	}
	
	@Override
	@RequestMapping("list")
	@ResponseBody
	public Response list(Integer start, Integer length, String value,String columnname) {
		int pageNumber = (int) (start / length) + 1;
		int pageSize = length;
		Page<M> m = simpleCurdService.findAll(pageNumber, pageSize, value,columnname);
		return new DataTableResponse<M>( m.getContent(),(int) m.getTotalElements() );
	}

	public SimpleCurdService<M, ID> getSimpleCurdService() {
		return simpleCurdService;
	}

	public void setSimpleCurdService(SimpleCurdService<M, ID> simpleCurdService) {
		this.simpleCurdService = simpleCurdService;
	}
}
