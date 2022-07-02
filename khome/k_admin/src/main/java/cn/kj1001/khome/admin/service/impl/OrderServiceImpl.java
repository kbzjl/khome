package cn.kj1001.khome.admin.service.impl;

import cn.kj1001.khome.base.entity.Order;
import cn.kj1001.khome.base.dao.OrderDao;
import cn.kj1001.khome.admin.service.OrderService;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public JsonResult updateOrderTimeout() {
        //1.查询过期订单是否存在，不存在则返回无需修改
        QueryWrapper<Order> qw = new QueryWrapper<Order>();
        //查询删除状态为0说明未删除存在的 支付状态lt小于2的(支付中的) 并且时间超过30分钟的
        qw.eq("del_state",0).lt("state",2)
                .last(" and now() - create_time >30");

        List<Order> orderList = orderDao.selectList(qw);
        if(orderList.isEmpty()){
            return  JsonResult.err(203,"没有要修改的订单");
        }
        //2.调用dao完成批量修改操作
        List<String> list = new ArrayList<String>(){{
            for (Order o:
                 orderList) {
                add(o.getId());
            }
        }};

        //使用流的方法处理
//        List<String> lsit = orderList.stream().map(Order::getId).collect(Collectors.toList());
//        orderDao.updateOrderTimeout(list);

        return JsonResult.ok();
    }

    /**
    * 参数介绍:
    * @description: 功能描述 查看订单
    * @author: MrZhang
    * @date: 2022/3/31 23:08 
    * @param: [parMap]
    * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  
    **/
    @Override
    public List<Map<String, Object>> getList(Map<String, Object> parMap) {
        return orderDao.selectOrderList(parMap);
    }
}
