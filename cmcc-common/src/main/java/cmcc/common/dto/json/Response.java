package cmcc.common.dto.json;

import io.swagger.annotations.ApiModelProperty;

public interface Response {
 	public static final String CODE_FAILED = "0"; 
    public static final String MSG_FAILED = "failed"; 
    public static final String CODE_SUCCESS = "1"; 
    public static final String MSG_SUCCESS = "success";
    public static final String CODE_EMPTY = "-1";
    public static final String MSG_EMPTY = "empty";
    
    @ApiModelProperty(value = "响应代码")
    String getCode();
    
    @ApiModelProperty(value = "描述")
	String getMsg();
}
