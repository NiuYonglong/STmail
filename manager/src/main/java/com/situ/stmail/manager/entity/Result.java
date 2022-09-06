package com.situ.stmail.manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Result {
    private Integer code;
    private String msg;
    private Object data;
    public static Result success(Integer code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result success(Object data){
        return success(0,null,data);
    }
    public static Result success(){
        return success(0,null,null);
    }
    public static Result error(Integer code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result error(Integer code,String msg){
        return error(code,msg,null);
    }
    public static Result error(String msg){
        return error(-1,msg,null);
    }
}
