package cn.kj1001.khome.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * kj1001
 *
 * @Description :统一异常类
 * @Author : MrZhang
 * @Date: 2022/3/24 14:49
 */

//给所有的controller返回前进行织入处理
@ControllerAdvice
@Slf4j
public class GlobalException {

    /**
    * 参数介绍:
    * @description: 功能描述
    * @author: MrZhang
    * @date: 2022/3/24 14:57
    * @param: [e]
    * @return: cn.kj1001.khome.base.util.JsonResult
    **/
    //异常处理器
    @ExceptionHandler(value = Exception.class)
    @ResponseBody//返回json
    public JsonResult handler(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return JsonResult.err(500,"未知异常");
    }
}
 
