package cn.kj1001.khome.common.aliPay;

import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/4/2 14:39
 */
@Slf4j
public class AliPayUtil {
    //appid
    private final static String app_id = "2021000119654897";
    //商户私密
    private final static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLN4zSjS1OwYX2lpZr8o6Fk1ShxBD7gLQWJnjgN7zOU/0gEIXuy8lNiW5VDSH1qKuO4S/gKLK2UdNO3pfeNj5BjtqoLPraVi9TGTVXe75mfAhikKI0Gj11LIWuQRZc8/f7WCT1Y2Xn+mXREI5+qVR/+vOrmDOS7Cht4a2H3vmCd/d/pxVNpCcvRejskryxaSgkR6TJytGIUL9uRhqF28aK4abnEnWmxAI+2MUBzTgaN+wYT00/UNrFToVgRlSWllQkOmU8CZlmC9JsxWmtDEdyXlzqtrij0wMd0olPxDXT+SvhUae3W0Q9wGQSp4dB+jK8/Y+0kV0L7QDAe1aUkRo3AgMBAAECggEAQwFWlxR6BRjh2GB8P7J75FCYAE/0+IfmpW6Y6RoKlqgYAbHSNLAC0H3gC7GJccpf4CIEP/h2o4fYk3JRWBN1fWo7VYxopGOs3WsZoGSfrIXwJziDyV4DzHzETq1nDRAjf+bZyv2vU6rSDIDShKYNVJe9Zg6YbBOcFd7z/ZxVLPTJAIGjp9rtp3DooBou+N5SkUeryaCmLz4dKa22rIRewre1j97z1nKo7U1bffJbPKjk7+2RqcPQcNo8WnKQDTANxH4fbLK12iK9OaJ97PDCrTlyTg8IELzbvCNGMgx3gscrXor7QgE2AvngfdD5JAuChfJ7w2ARHQ8kaUbp9mIAqQKBgQC+G3Jm0W6gscb2AVH2orz3NqodqJUY5BE6cvriDUh0zwJMyp9y1PLQRj6L9DCKEKO9RYgOh1B//C50lELi0Xyx1F2SHUOFHYUrIv7G0Ff9MaQmaKXd2LkKSQj/wBOyu+2ZaTfK8xTPf5hrGFDr1jmvp1gNRlNbuc9UHMqhzKAP8wKBgQC7eIXW3a0mUFF1bkUGmknEGNzx6pgpHNkZ/9shA5/6UypVI7V1Utskavgur8scBBM/sIymxzb/UnUFb+5a8SDI1W9fz5X0n9ksTgoAc4Q9J2WLIvonSaMcWK/W9cYrPL1j83v6J42j971JAuWV2m5ykKKIht4DrnTkZoB2mkghrQKBgQCT81BpbHyoWzL4qbZcN/LQgp6KTnUa1gQCdRGDc8r7/U9Q7DCDce824DQ1P2m3SZPk7YQhqnWf5WdSG0CivV5KF3hrCpNNude15ppZqDOSPVv++Fp9+PipZIW4ZIfwBgm35wcgz2VWx3qbfmUb5bEnXxlL23tsW4NbdrzMhy/auQKBgFD9g4zNHhXUUyzRY76zfzTZrPJScEQqs1w5HdkIcN+7eClqEOXL0uLL18aLqcrDs/Eq+8i9ffQWEnoz3hyp2YkKNGCHFPim6jLq0AVb4oBs+cB/buT0OiRv6B+M9H0Pc56vvRrYZae1yq+1Maxovph+BcnHwjiC8PsPkWYrxwfNAoGAa0CFhK1xgROpdAhKtN7L32DLZQdGUIBFuEFdwj2YtbKwYzxL6op1KKUZ1twAjag3tJcODYe7Bg85uW3yaoZsJu+45uYGZJDV9CmNNjjszfOvcmdWsU4I6GZsY4axjmJQCImFIdT9uah+y4PxCSHFkIaBg0UQIWIyTykgOiL8730=";
    //支付宝公密
    private final static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqHEocHDYc1nHAVxyg9rJmtB3YK8BZeaZ3fjPN49uWb1+ODGSiFXeFt0mOmIvhOxqLDav9YX/2iWevvKxxFLsB4g2kPQ+pwU18KINNIKrp3Pqd1Z2TbxzYLcvXF84PiHQaBwDk7SxuzkXyhZRJRxzUdFxa2HJff8e8u0exo4ZKCN7CJeEMr7rxofCfa5xgkh6BTOwQTHKn1GtPDf6UYb1ua5qdCt6ESdhPLxxQtOEnM7ZpVsAk0pLFBhTgrxnSFTcheVDG2H38yGUvaz5FdWHD1midPiVSH+L8fULi6SCSTB2yt+gM+GCDmHKZRwAAceuEYE9+bVvAsDjSWbVFj9v6QIDAQAB";
    //同步回调
    private final static String return_url= "http://22t29y5653.51vip.biz:45967/alipay/callback";
    //异步回调
    private final static String notify_url= "http://22t29y5653.51vip.biz:45967/alipay/callback";
    //加密方式
    private final static String sign_type = "RSA2";
    //编码方式
    private final static String charset = "utf-8";
    //调用的接口-沙箱环境的接口，如果要上线，修改密钥和url就可以
    private final static String getwayUrl = "https://openapi.alipaydev.com/gateway.do";



    /**
    * 参数介绍:
    * @description: 功能描述 通过系统订单，返回支付宝订单
    * @author: MrZhang
    * @date: 2022/4/2 16:06 
    * @param: [bean]
    * @return: java.lang.String  
    **/
    public static String getAliPayOrder(PayBean bean) throws AlipayApiException {
        //根据常量信息创建阿里支付的客户端
        AlipayClient client = new DefaultAlipayClient(
                getwayUrl,
                app_id,
                private_key,
                "json",
                charset,
                public_key,
                sign_type
        );

        //创建支付请求
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        //同步回调地址
        payRequest.setNotifyUrl(notify_url);
        //同步回调
        payRequest.setReturnUrl(return_url);
        String payinfo = JSONUtil.toJsonStr(bean);
        log.info("支付信息"+payinfo);
        payRequest.setBizContent(payinfo);
        //发送请求并返回
        String body  = client.pageExecute(payRequest).getBody();
        log.info("支付返回信息"+body);
        return  body;
    }

    /**
    * 参数介绍:
    * @description: 功能描述 验证签名 确认是不是支付宝发的
    * @author: MrZhang
    * @date: 2022/4/4 17:50
    * @param: [parMap]
    * @return: boolean
    **/
    public static boolean rsaCheck(Map<String,String> parMap) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(
                parMap,
                public_key,
                charset,
                sign_type);

    }

//    public static void main(String[] args) throws AlipayApiException {
//        PayBean payBean = new PayBean();
//        payBean.setOut_trade_no("商家订单编号gs001");
//        payBean.setTotal_amount("18");
//        payBean.setSubject("名称--测试用的订单");
//        payBean.setBody("订单描述:我是测试的");
//
//        String order = getAliPayOrder(payBean);
//
//    }

}
 
