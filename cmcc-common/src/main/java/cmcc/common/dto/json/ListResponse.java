package cmcc.common.dto.json;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ListResponse<T> implements Response {
	private String code;
	private String msg;
    private List<T> datas;

    public ListResponse(List<T> datas) {
        this.code = "1";
        msg = "success";
        this.datas = datas;
    }

    public ListResponse(String code, String msg, List<T> datas) {
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }
    
    public List<T> getDatas() {
        return datas;
    }

  

	@Override
	public String getCode() {
		return code;
	}

	@ApiModelProperty(value = "请求结果")
	@Override
	public String getMsg() {
		return msg;
	}
}
