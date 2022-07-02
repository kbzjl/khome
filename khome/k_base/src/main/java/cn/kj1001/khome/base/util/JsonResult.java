package cn.kj1001.khome.base.util;

import lombok.Getter;

/**
 * kj1001
 *
 * @Description : 统一返回json格式
 * @Author : MrZhang
 * @Date: 2022/2/18 11:23
 */
@Getter
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;

    //构造方法 私有化
    private JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok(Integer code,String msg) {
        return new JsonResult(code,msg,null);

    }
    //响应成功返回json
    public static JsonResult ok(){
        return new JsonResult(200,"操作成功",null);
    }

    //响应成功返回json
    public static JsonResult ok(Object data) {
        return new JsonResult(200,"操作成功",data);
    }

    //响应失败的返回json
    public static JsonResult err(Integer code,String msg) {
        return new JsonResult(code,msg,null);

    }



}
 
