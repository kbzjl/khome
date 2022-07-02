package cn.kj1001.khome.base.dao;

import cn.kj1001.khome.base.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface UserDao extends BaseMapper<User> {
   //查询所有
    List<User> selectList();

    //查询用户 添加先查询
    List<User> selectListByEntity(User user);

    //查询用户 如果用时间区间开始和结束时间 查询用这个
   // List<User> selectUserInfo(Map<String,Object> parmap);


    //删除用户
    Integer delUser(User user);

    //修改用户
    Integer updateUser(User user);

}
