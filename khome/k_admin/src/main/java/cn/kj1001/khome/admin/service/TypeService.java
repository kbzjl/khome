package cn.kj1001.khome.admin.service;

import cn.kj1001.khome.base.entity.Type;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库类型表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface TypeService extends IService<Type> {
    /**
    * 参数介绍:
    * @description: 功能描述 查询题库类型列表
    * @author: MrZhang
    * @date: 2022/3/29 08:47
    * @param:
    * @return:
    **/
    List<Type> selectList(Map<String,Object> parMap);

    /**
    * 参数介绍:
    * @description: 功能描述 添加题库类型
    * @author: MrZhang
    * @date: 2022/3/29 80:49
    * @param:
    * @return:
    **/
    JsonResult addType(Type type);


    /**
    * 参数介绍:
    * @description: 功能描述 修改题库类型
    * @author: MrZhang
    * @date: 2022/3/29 20:53
    * @param: [type]
    * @return: cn.kj1001.khome.base.util.JsonResult
    **/

    JsonResult updateType(Type type);


}
