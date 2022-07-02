package cn.kj1001.khome.common.controller;

import cn.kj1001.khome.common.aliPay.AliPayUtil;
import cn.kj1001.khome.common.aliPay.PayBean;
import com.alipay.api.AlipayApiException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/4/2 16:06
 */
@Slf4j
@RestController
@RequestMapping("alipay")
public class AlipayController {
    /**
    * 参数介绍:
    * @description: 功能描述
    * @author: MrZhang
    * @date: 2022/4/4 17:42
    * @param: [parMap] orderId money
    * @return: java.lang.String
    **/
    @PostMapping("/order")
    public String getAliOrder(@RequestParam Map<String,Object> parMap) throws AlipayApiException {
        PayBean payBean = new PayBean()
                .setOut_trade_no(parMap.get("orderId").toString())
                .setTotal_amount(parMap.get("money").toString())
                .setSubject("MAC笔记本一台");

        return AliPayUtil.getAliPayOrder(payBean);

    }

    /**
    * 参数介绍:
    * @description: 功能描述 提供给阿里平台进行回调使用
    * @author: MrZhang
    * @date: 2022/4/4 17:46
    * @param:
    * @return:
    **/
    @PostMapping("callback")
    public String callBack(@RequestParam Map<String,String> parMap) throws AlipayApiException {
        log.info(parMap.toString());
        //签名认证
        if(AliPayUtil.rsaCheck(parMap)){
            //调用业务逻辑判断 完成支付后的逻辑 修改时使用行锁完成修改处理 2-3
            log.info("业务逻辑处理完成");
        }
        return "ok";


    }

}
 
