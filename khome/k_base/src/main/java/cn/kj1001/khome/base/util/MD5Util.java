package cn.kj1001.khome.base.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/29 14:55
 */
@Component
public class MD5Util {

    private MD5Util() {}

    /**
     * 加密方法 使用盐进行加密
     * MD5摘要加密 加密时 使用加密前内容 部分内容完成加密 理论不可逆
     * @param str
     * @return
     */
    @Value("${info.pwd}")
    private static String key;

    public static String en(String str) {
        //1.调用Spring下的加密工具完成MD5加密
        str += key;
        String md5Str = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5Str;
    }

    /**
     * 完成摘要验证
     * @param enStr MD5字符串
     * @param str 未加密的字符串
     * @return
     */
    public static Boolean check(String enStr, String str) {
        return enStr.equals(en(str));
    }

    //测试
    public static void main(String[] args) {
        String str="123456";
        //完成加密
        String enStr=en(str);
        System.out.println("完成加密"+enStr);

        //调用验证
        System.out.println("验证结果"+check(enStr,str));
    }

}
 
