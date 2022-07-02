package cn.kj1001.khome.base.util;


import cn.hutool.core.util.RandomUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/27 11:24
 */
@Slf4j
public class AliSmsUtil {
    //需要的信息

    //阿里账号，用户keyid
    private static final String ACCESS_KEY="LTAI4G6dR2uLfBM4u3dg2c53";
    //安全码
    private static final String SECRET="08ND01MXW1sGKj8WgBE3h5ZzPpWNyU";
    //签名-短信的前缀
    private static final String SGIN="Aedes商城";
    //短信模板编码
    private static final String TEMPLATE="SMS_206560024";
    //短信发送源
    private static final String ENDPOINT="dysmsapi.aliyuncs.com";
    /**
    * 参数介绍:
    * @description: 功能描述 发送短信
    * @author: MrZhang
    * @date: 2022/3/27 11:58
    * @param: [phone, code]
    * @return: cn.kj1001.khome.base.util.JsonResult
    **/

    public static JsonResult sendSms(String phone,String code) throws Exception {
        //完成客户端的创建
        Config config = new Config().setAccessKeyId(ACCESS_KEY).setAccessKeySecret(SECRET);
        config.endpoint=ENDPOINT;
        Client client = new Client(config);

        //完成短信内容的拼接
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(SGIN)
                .setTemplateCode(TEMPLATE)
                .setTemplateParam("{\"code\":\""+code+"\"}");


        //发送短信
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        //判断发送结果
        String rescode = sendSmsResponse.getBody().getCode();

        if(rescode.equals("OK")){
            return null;
        }

        //打印日志查看是否成功
        log.error("短信发送失败:"+phone+";message:"+sendSmsResponse.getBody().getMessage());
        return JsonResult.err(203,"短信发送失败,请联系管理员");

    }

    //发送int num 是数字
    public static String getRandomCode(int num){
        return RandomUtil.randomNumbers(num);
    }

      //发送字符String str 可以发指定的也可以发随机的
//    public static String getRandomCode(String str){
////        str ="hello";
//        return RandomUtil.randomString(4);
//
//    }

//    public static void main(String[] args) throws Exception {
//        //测试发送短信
//        //System.out.println(sendSms("18182421361","ooii"));
//        System.out.println(sendSms("18182421361",getRandomCode(4)));
//    }


}
 
