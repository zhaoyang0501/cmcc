package cmcc.core.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.common.service.SimpleCurdService;
import cmcc.core.sys.entity.Deptment;
import cmcc.core.sys.repository.DeptmentRepository;
@Service
public class DeptmentService  extends SimpleCurdService<Deptment, Long> {
	@Autowired
	public DeptmentRepository deptmentRepository;
	
	public List<Deptment> queryRootList(){
		return this.deptmentRepository.queryByParent(null);
	}
}
