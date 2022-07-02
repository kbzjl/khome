package cn.kj1001.khome.admin.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;



/**
 * kj1001
 *
 * @Description : 定时器处理订单实现原理
 * @Author : MrZhang
 * @Date: 2022/3/26 11:33
 */
//继承一个定时任务对象
@Slf4j
public class OrderJobBean extends QuartzJobBean {

    //执行任务
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("订单任务失效开启----------");
        log.info("订单任务失效结束----------");
    }
}
 
