package cn.kj1001.khome.admin.service.impl;

import cn.kj1001.khome.base.entity.Admin;
import cn.kj1001.khome.base.dao.AdminDao;
import cn.kj1001.khome.admin.service.AdminService;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AdminDao adminDao;

    @Override
    public JsonResult login(Map<String, Object> parMap) {
        QueryWrapper<Admin> qw = new QueryWrapper<Admin>().eq("phone",parMap.get("phone"));
        List<Admin> list = adminDao.selectList(qw);
        if(list.isEmpty()){
            return JsonResult.err(205,"用户不存在");
        }
        //如果查询到多个用户 说明有重复用户存在
        if(list.size()>1){
            return JsonResult.err(203,"用户状态异常");
        }
        Admin admin = list.get(0);
        if(!admin.getPassword().equals(parMap.get("password"))){
            return JsonResult.err(203,"用户名或密码错误");

        }

        //生成token并放入redis
        String token = redisUtil.createToken();
        redisUtil.setToken(token,list.get(0).getId().toString());

        //完成返回内容的拼接
        Map<String,Object> reMap = new HashMap<>();
        reMap.put("admin",list.get(0).setPassword(""));
        reMap.put("token",token);

       return JsonResult.ok(reMap);
    }
}
