package cn.kj1001.khome.admin.quartz;

import cn.kj1001.khome.admin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * kj1001
 *
 * @Description : 注解的方式实现处理订单
 * @Author : MrZhang
 * @Date: 2022/3/26 14:47
 */
@Component
@Slf4j
@EnableScheduling //调度器
public class QuartzScheduleAn {
    @Autowired
    OrderService orderService;
    @Scheduled(cron = "0/8 * * * * ?") //每8秒一次
    public void job(){
        log.info("订单失效任务开启-----注解的方式实现");
        log.info(orderService.updateOrderTimeout().toString());//调用修改订单的方法传入
        log.info("订单失效任务结束-----注解的方式实现");
    }
}
 
