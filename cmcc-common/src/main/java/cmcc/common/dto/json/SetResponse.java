package cmcc.common.dto.json;

import java.util.Set;

import io.swagger.annotations.ApiModelProperty;

public class SetResponse<T> implements Response {
	
	private String code;
	private String msg;
    private Set<T> datas;

    public SetResponse(Set<T> datas) {
    	 this.code = "1";
         this.msg = "success";
        this.datas = datas;
    }

   
    public SetResponse(String code, String msg, Set<T> datas) {
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }

    @ApiModelProperty(value = "请求结果")
    public Set<T> getDatas() {
        return datas;
    }

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
