package com.shirs.agileboot.common.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shirs.agileboot.exception.BaseErrorInfoInterface;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult() {

    }

    public ResponseResult(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "success";
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult success(T data) {
        return new ResponseResult(data);
    }

    public ResponseResult(BaseErrorInfoInterface errorInfoInterface) {
        this.code = errorInfoInterface.getResultCode();
        this.msg = errorInfoInterface.getResultMsg();
    }

    public static ResponseResult error(BaseErrorInfoInterface errorInfo) {
        return new ResponseResult(errorInfo.getResultCode(), errorInfo.getResultMsg());
    }

    public static ResponseResult error(Integer code, String msg) {
        return new ResponseResult(code, msg);
    }
}
