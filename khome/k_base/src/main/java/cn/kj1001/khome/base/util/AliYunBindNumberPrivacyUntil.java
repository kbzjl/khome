package cn.kj1001.khome.base.util;
import com.aliyun.dyplsapi20170525.Client;
import com.aliyun.tea.*;
import com.aliyun.dyplsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName BindAxnUntil
 * @Description: TODO
 * @Author zl 阿里号码隐私保护  AXN隐私号是为A号码分配一个专属的隐私号，任何号码N通过拨打X号码都可以转接到A
 * @Date 2022/4/18
 * @Version V1.0
 **/
public class AliYunBindNumberPrivacyUntil {
    private static final Logger logger = LoggerFactory.getLogger(AliYunBindNumberPrivacyUntil.class);

    private static final String userPrincipalName = "pnp@1034091764492781.onaliyun.com";
//     private static final String accessKeyID = "LTAI5t9EYpB85m5ZU3W2RaDH";
//    private static final String accessKeySecret = "iTpeUZdkqfNaRHkQf9GWUX0bO4VpHp";

    private static final String accessKeyID = "LTAI5t72j9DUgGxKEjwkd6wY";
    private static final String accessKeySecret = "2LA33ySE0ASfuF8mds3VHosrkfy6Ag";



    private static final String poolKey = "iTpeUZdkqfNaRHkQf9GWUX0bO4VpHp";
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dyplsapi.aliyuncs.com";
        return new Client(config);
    }


    /**
     * 使用说明：绑定AXB号码前，建议明确您的业务场景中AXB设备证书（ProductKey、DeviceName、DeviceSecret）
     * 的A角色和B角色。例如，在打车应用场景中，A可以是乘客角色，B是司机角色；房产类业务场景中，A可能是用户，B是房产中介。
     * @param phoneNoA
     * @param phoneNoB
     * @param phoneNoX
     * @param callRestrict 单通呼叫限制的状态。取值：CONTROL_AX_DISABLE：A号码无法呼叫X号码。CONTROL_BX_DISABLE：B号码无法呼叫X号码。
     * @return
     */
    public static String bindAxb(String phoneNoA, String phoneNoB, String phoneNoX,String expirationDate) {
        String response = "";
        try {
            Client client = AliYunBindNumberPrivacyUntil.createClient(accessKeyID, accessKeySecret);
            BindAxbRequest bindAxbRequest = new BindAxbRequest()
                    .setPoolKey(poolKey)//号码池Key
                    .setPhoneNoA(phoneNoA)//AXB中的A号码。A号码可设置为手机号码或固定电话
                    .setPhoneNoB(phoneNoB)//AXB中的B号码，A号码拨打X号码时会转接到B号码
                    .setPhoneNoX(phoneNoX)//X号码是您绑定号码前在号码隐私保护控制台或通过BuySecretNo接口购买的电话号码，用于转接电话
                    .setExpiration(expirationDate)//绑定关系的过期时间
                    .setExpectCity("北京")
                    .setCallRestrict("CONTROL_AX_DISABLE")
                    .setCallDisplayType(1)
                    .setIsRecordingEnabled(false)//可选:是否需要录制音频-默认是false
                    .setCallTimeout(10);
            // 复制代码运行请自行打印 API 的返回值
            BindAxbResponse resp = client.bindAxb(bindAxbRequest);

            if (resp == null) {
                throw new Exception("阿里云绑定AXB号码请求结果失败");
            }

            BindAxbResponseBody responseBody = resp.getBody();
            if (responseBody.getCode() != null && responseBody.getCode().equals("OK")) {
                //请求成功
                response = CommonUtils.toJson(responseBody.getSecretBindDTO());
            }
        } catch (Exception e) {
            logger.error("阿里云绑定AXB号码失败phoneNoA=" + phoneNoA + "，phoneNoB=" + phoneNoB, e);
        }
        return response;
    }


    /**
     * 订购关系解绑示例(解绑接口不区分AXB、AXN)
     * @param poolKey
     * @param subsID
     * @param secretNo
     * @return
     */
    public static boolean unbindSubscription(String poolKey,String subsID, String secretNo) {
        boolean ifUnbind = false;
        try {
            com.aliyun.dyplsapi20170525.Client client = AliYunBindNumberPrivacyUntil.createClient(accessKeyID, accessKeySecret);
            UnbindSubscriptionRequest unbindSubscriptionRequest = new UnbindSubscriptionRequest()
                    .setPoolKey(poolKey)
                    .setProductType("AXB_170")
                    .setSubsId(subsID)//绑定关系ID 可以在控制台的号码管理 > 号码详情中查看绑定关系ID，或者在调用BindAxb等号码绑定API时查看返回参数中的SubsId
                    .setSecretNo(secretNo);//隐私号码。调用BindAxb等号码绑定接口时指定或自动分配的X号码
            // 复制代码运行请自行打印 API 的返回值
            UnbindSubscriptionResponse resp = client.unbindSubscription(unbindSubscriptionRequest);

            if (resp == null) {
                throw new Exception("阿里云解除号码的绑定关系请求结果失败");
            }

            UnbindSubscriptionResponseBody responseBody = resp.getBody();
            if (responseBody.getCode() != null && responseBody.getCode().equals("OK")) {
                //请求成功
                ifUnbind = true;
            }
        } catch (Exception e) {
            logger.error("阿里云解除号码的绑定关系失败 ", e);
        }
        return ifUnbind;
    }


    /**
     * 说明 AXN隐私号是为A号码分配一个专属的隐私号，任何号码N通过拨打X号码都可以转接到A
     * @param phoneNoA
     * @param phoneNoB
     * @param phoneNoX
     * @return
     */
    public static Map<String, Object> bindAxn(String poolKey,String phoneNoA, String phoneNoB, String phoneNoX,String expirationDate) {
        try {
            Client client = AliYunBindNumberPrivacyUntil.createClient(accessKeyID, accessKeySecret);
            BindAxnRequest bindAxnRequest = new BindAxnRequest()
                    .setPoolKey(poolKey)
                    .setPhoneNoA(phoneNoA)
                    .setPhoneNoB(phoneNoB)
                    .setPhoneNoX(phoneNoX)
                    .setExpiration(expirationDate)
                    .setExpectCity("北京")
                    .setIsRecordingEnabled(false)
                    .setCallDisplayType(1)
                    .setCallTimeout(10)
                    .setCallRestrict("CONTROL_AX_DISABLE");
            BindAxnResponse resp = client.bindAxn(bindAxnRequest);
            return TeaModel.buildMap(resp);
        } catch (Exception e) {
            logger.error("阿里云绑定Axn号码失败 phoneNoA="+phoneNoA+"，phoneNoB="+phoneNoB, e);
        }
        return null;
    }
    /**
     *
     */

    /**
     *
     * @param args_
     * @throws Exception
     */
    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
//        Client client = AliYunBindNumberPrivacyUntil.createClient("ACCESS_KEY_ID", "ACCESS_KEY_SECRET");
        Client client = AliYunBindNumberPrivacyUntil.createClient(accessKeyID, accessKeySecret);
        BindAxbRequest bindAxbRequest = new BindAxbRequest()
                .setPoolKey(poolKey)
//                .setPhoneNoA("18706860934")
//                .setPhoneNoB("15229226837")
                .setPhoneNoA("13209107937")
                .setPhoneNoB("13227942867")
                .setPhoneNoX("123456")
                .setExpiration("2022-04-23 12:00:00")
                .setExpectCity("北京")
                .setIsRecordingEnabled(false)
                .setCallDisplayType(1)
                .setCallTimeout(10)
                .setCallRestrict("CONTROL_AX_DISABLE");
        BindAxbResponse resp = client.bindAxb(bindAxbRequest);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
    }

}
