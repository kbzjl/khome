package cn.kj1001.khome.admin.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * kj1001
 *
 * @Description : 任务调度器类，定时任务的容器，规定时间完成任务的调度和计划
 * @Author : MrZhang
 * @Date: 2022/3/26 11:42
 */
@Configuration//指示一个类声明一个或多个@Bean方法，并且可以由Spring容器处理
public class QuartzSchadule {

    //1.创建任务的bean
    @Bean
    public JobDetail orderDetail(){
        //将OrderJobBean类实例化 执行这个实例(OrderJobBean)并持久化
        return JobBuilder.newJob(OrderJobBean.class).storeDurably().build();
    }


    //2.触发器方法
    @Bean
    public Trigger orderTrigger(){
        //使用模板创建调度器 构造一个简单的调度器 以秒为单位 循环反复执行
        SimpleScheduleBuilder builder1 = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        //通过cron的语法完成调度器的创建 创建一个计划任务
        CronScheduleBuilder   builder2 = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

        //通过调度器和任务，创建触发器 确认要执行的时间并完成任务的触发
        return TriggerBuilder.newTrigger().forJob(orderDetail()).withSchedule(builder2).build();
    }

}
 
