package cn.kj1001.khome.admin.service;

import cn.hutool.extra.ssh.JschRuntimeException;
import cn.kj1001.khome.base.entity.Order;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface OrderService extends IService<Order> {
    /**
    * 参数介绍:
    * @description: 功能描述
     *              提供定时任务调用，完成过期订单的状态修改
     *              1.查询过期订单是否存在，不存在则返回无需修改
     *              2.调用dao完成批量修改操作
    * @author: MrZhang
    * @date: 2022/3/26 15:51
    * @param: []
    * @return: cn.kj1001.khome.base.util.JsonResult  
    **/
    JsonResult updateOrderTimeout();


    /**
    * 参数介绍:
    * @description: 功能描述 查看订单列表
    * @author: MrZhang
    * @date: 2022/3/31 23:06 
    * @param: [parMap] 选填 用户名称,手机号,支付状态,支付方式,创建时间区间]
    * @return: 订单id,用户名称,手机号,金额,类型,课程名称,题库名称,创建时间,支付时间,状态
    **/
    List<Map<String,Object>> getList(Map<String,Object> parMap);

}
