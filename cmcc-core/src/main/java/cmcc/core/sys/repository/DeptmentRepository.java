package cmcc.core.sys.repository;
import java.util.List;

import cmcc.common.repository.SimpleCurdRepository;
import cmcc.core.sys.entity.Deptment;
public interface DeptmentRepository   extends SimpleCurdRepository<Deptment ,Long>{
	public List<Deptment> queryByParent(Deptment dept);
}
