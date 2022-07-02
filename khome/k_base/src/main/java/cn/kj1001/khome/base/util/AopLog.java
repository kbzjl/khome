package cn.kj1001.khome.base.util;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/24 13:29
 */
@Slf4j
@Aspect
@Component
public class AopLog {

    /**
    * 参数介绍:
    * @description: 功能描述  声明切点
    * @author: MrZhang
    * @date: 2022/3/24 13:41 
    * @param: []
    * @return: void  
    **/
    //定义切点
    @Pointcut("execution(public * cn.kj1001.khome..controller.*.*(..))")
    public void controllerPoint(){}
    
    /**
    * 参数介绍:
    * @description: 功能描述 前置增强切面
    * @author: MrZhang
    * @date: 2022/3/24 13:42 
    * @param: 
    * @return:   
    **/
    @Before("controllerPoint()")
    public void beforAop(JoinPoint joinPoint){
     log.info("====================访问开始===================");
        //获取request
        ServletRequestAttributes att = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = att.getRequest();

        //完成日志显示
        log.info("访问ip"+request.getRemoteAddr());
        log.info("访问路径"+request.getServletPath());
        log.info("访问方式"+request.getMethod());
        log.info("处理的方法"+joinPoint.getTarget().toString()+":"+joinPoint.getSignature().toString());
        log.info("访问的参数"+joinPoint.getArgs().toString());
        }

        /**
        * 参数介绍:
        * @description: 功能描述 后置增强 日志返回前的操作
        * @author: MrZhang
        * @date: 2022/3/24 14:15
        * @param: [obj]
        * @return: void  
        **/

    @AfterReturning(pointcut = "controllerPoint()",returning = "obj")
    public void afterAop(Object obj){
        log.info("返回信息"+ JSONArray.toJSONString(obj));
        log.info("====================访问结束=========================");

    }
}

