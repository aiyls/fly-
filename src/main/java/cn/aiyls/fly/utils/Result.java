package cn.aiyls.fly.utils;

import cn.aiyls.fly.enums.ReturnCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Data
public class Result<T> {

    private int code;

    private String message;

    private String tips;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result(){

    }

    public Result(int code,String message){
        this.code=code;
        this.message=message;
    }

    public Result(int code, String message, String tips) {
        super();
        this.code = code;
        this.message = message;
        this.tips = tips;
    }

    public Result(int code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    public Result(int code, String message, String tips, T data) {
        super();
        this.code = code;
        this.message = message;
        this.tips = tips;
        this.data = data;
    }

    public Result(ReturnCodes returnCodes){
        this.code=returnCodes.getCode();
        this.message=returnCodes.getTips();
        this.tips=returnCodes.getMessage();
    }

    public Result(ReturnCodes returnCodes,T data){
        this.code=returnCodes.getCode();
        this.message=returnCodes.getTips();
        this.tips=returnCodes.getMessage();
        this.data=data;
    }

    public static Result ok() {
        Result result = new Result(ReturnCodes.success, "请求成功");
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result(200, "请求成功", data);
        return result;
    }
    public static Result failure(Object data) {
        Result result = new Result(200, "请求成功", data);
        return result;
    }
}
