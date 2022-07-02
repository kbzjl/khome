package cn.kj1001.khome.common.service;

import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.JsonResult;

import java.util.List;
import java.util.Map;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/29 10:09
 */
public interface UserService {
     //注册
     JsonResult addUser(Map<String,Object> parMap);
     //登录
     JsonResult login(User user);
}
 
