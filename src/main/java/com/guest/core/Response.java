package com.guest.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * 统一响应体
 * @author lxy
 * @since 2022-03-02
 *
 * @param <T>
 */
@Data
public class Response<T> implements Serializable {
    /** 返回信息码*/
    private int code;
    /** 返回信息内容*/
    private String msg;

    private T data = null;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp = new Date();

    public Response(){}
    //只能返回定义的返回信息
    //根据异常类构造
    public static Response success(Object data){
        Response response = new Response();
        response.code = ResponseMsg.SUCCESS.code;
        response.msg = ResponseMsg.SUCCESS.msg;
        response.data = data;
        return response;
    }
    public static Response success(){
        return new Response(ResponseMsg.SUCCESS);
    }
    public Response(ResponseMsg msg){
        this.code = msg.code;
        this.msg = msg.msg;
    }
    public static Response fail(){return new Response(ResponseMsg.FAIL);}
    public static Response fail(String msg){
        Response response = new Response();
        response.code = ResponseMsg.FAIL.code;
        response.msg = msg;
        return response;
    }

}
