package cn.kj1001.khome.common.aliPay;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/4/2 14:22
 */
@Data
@Accessors(chain = true) //链式编程
public class PayBean {
    //订单在本地系统中的唯一标识，必填 商户订单号,由商家自定义
    private String out_trade_no;
    //支付金额，必填	订单总金额
    private String total_amount;
    //订单名称，必填
    private String subject;
    //订单描述，选填
    private String body;
    //超时 时间10分钟
    private String timeout_express="10m";
    //使用即时支付
    private String product_code="FAST_INSTANT_TRADE_PAY";

}
 
