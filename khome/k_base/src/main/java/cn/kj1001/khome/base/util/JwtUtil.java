package cn.kj1001.khome.base.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import cn.kj1001.khome.base.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;


import java.util.Date;
import java.util.Map;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/4/10 14:30
 */
@Slf4j
public class JwtUtil {
    //加密的密匙
    private static String sign = "kj1001";
    
    /**
    * 参数介绍:
    * @description: 功能描述 通过用户生成jwt的token
    * @author: MrZhang
    * @date: 2022/4/10 14:38
    * @param: [user]
    * @return: java.lang.String  
    **/
    public static String getToken(User user){
        try {
            Map<String,Object> map = BeanUtil.beanToMap(user);
            Date now = new Date();
            return JWT.create()
                    .withHeader(map)// 登录后的用户数据
                    .withIssuedAt(now)//签发时间
                    .withExpiresAt(new Date(now.getTime()+1*60*1000))//过期时间
                    .sign(Algorithm.HMAC256(sign));//加密后的签名
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User deCodeByToken(String token){
        //生成jwt验证器
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();

            //验证并获取jwt对象
            DecodedJWT jwt = jwtVerifier.verify(token);
            log.info(jwt.getHeader());

            //获取用户json字符串
            String json = StringUtils.newStringUtf8(Base64.decode(jwt.getHeader()));
            log.info(json);
            return JSONUtil.toBean(json,User.class);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        User user = new User().setUserName("admin").setPassword("123456");
        String token = getToken(user);
        System.out.println(token);
        user = deCodeByToken(token);
        System.out.println(user);
    }
}
 
