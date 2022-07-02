package cn.kj1001.khome.admin.service;

import cn.kj1001.khome.base.entity.Info;
import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家信息表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface InfoService extends IService<Info> {
    /**
    * 参数介绍:
    * @description: 功能描述  查询商家列表
    * @author: MrZhang
    * @date: 2022/3/23 14:43
    * @param: [parMap]
    * @return: java.util.List<cn.kj1001.khome.base.entity.User>
    **/

    //查询商家列表
    List<Info> getList(Map<String,Object> parMap);

    //查询商家
    List<Info> getSto(Map<String,Object> parMap);

    //修改用户
    JsonResult updateSto(Info info);

    //添加用户信息
    JsonResult addSto(Info info);
}
