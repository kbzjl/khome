package cn.kj1001.khome.base.util;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/29 11:50
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //创建token
    public  String createToken(){
        return IdUtil.simpleUUID();
    }

    //将token存入redis
    public void setToken(String token,String userId){
        //通过操作模板获取操作处理对象
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(token,userId,30*60,TimeUnit.SECONDS);
    }
    public String getToken(String token){
        return redisTemplate.opsForValue().get(token);
    }


    //存入redis 短信使用的
    public void setCode(String phone,String code){
        //通过操作模板获取操作处理对象
        redisTemplate.opsForValue().set(phone,code,60*5, TimeUnit.SECONDS);

    }

    public String getPhone(String phone){
        return redisTemplate.opsForValue().get(phone);

    }

    //删除key
    public void delphone(String phone){
        redisTemplate.delete(phone);
    }


}


 
