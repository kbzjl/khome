package cn.kj1001.khome.admin.service;

import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;
import jdk.security.jarsigner.JarSigner;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface UserService extends IService<User> {
    /**
    * 参数介绍:
    * @description: 功能描述 通过参数获取列表信息
    * @author: MrZhang
    * @date: 2022/3/21 13:06 
    * @param: [parMap]
    * @return: cn.kj1001.khome.base.util.JsonResult  
    **/
    List<User> getList(Map<String,Object>parMap);
    
    /**
    * 参数介绍:
    * @description: 功能描述 新增用户
    * @author: MrZhang
    * @date: 2022/3/21 13:06
    * @param: [user]
    * @return: cn.kj1001.khome.base.util.JsonResult  
    **/
    JsonResult addUser(User user);

    //查询用户信息 如果需要分页用list
    List<User> selectListByUser(Map<String,Object> parMap);

   //删除用户
    JsonResult delUser(User user);

    //修改会员等级
    JsonResult updateUser(User user);

}
