package cn.kj1001.khome.base.dao;

import cn.kj1001.khome.base.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface OrderDao extends BaseMapper<Order> {
    //根据id修改订单时间
    int updateOrderTimeout(List<String> ids);
    /**
    * 参数介绍:
    * @description: 功能描述 查看订单信息
    * @author: MrZhang
    * @date: 2022/3/31 13:41
    * @param: [parMap]选填 用户名称,手机号,支付状态,支付方式,创建时间区间]
    * @return: 订单id,用户名称,手机号,金额,类型,课程名称,题库名称,创建时间,支付时间,状态
    **/
    List<Map<String,Object>> selectOrderList(Map<String,Object> parMap);

}
