package cn.kj1001.khome.common.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj1001.khome.base.dao.UserDao;
import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.JwtUtil;
import cn.kj1001.khome.base.util.MD5Util;
import cn.kj1001.khome.base.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * kj1001
 *
 * @Description :
 * @Author : MrZhang
 * @Date: 2022/3/29 10:10
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Autowired
    RedisUtil redisUtil;


    @Override
    @Transactional
    public JsonResult addUser(Map<String, Object> parMap) {

        //查询redis是否过期
        String code = redisUtil.getPhone(parMap.get("phone").toString());
        //判断验证码是否正确
        if (code==null){
            return JsonResult.err(201,"验证码已过期");
        }
        //判断验证码是否正确
        if(!code.equals(parMap.get("code"))){
            return JsonResult.err(205,"验证码错误");

        }

        //整合参数 将参数存入对象中取
        User user = BeanUtil.toBean(parMap,User.class);
        user.setLevel(0)
                .setAttestType(0)
                .setCreateTime(LocalDateTime.now())
                .setDelState(0)
                .setPassword(MD5Util.en(user.getPassword()));
        //查询手机号是否存在
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("phone",parMap.get("phone").toString());
        User oldUser = userDao.selectOne(qw);
        //判断使用有老用户
        if(oldUser!=null){
            //判断删除状态
            if(oldUser.getDelState()==0){
                return JsonResult.err(205,"用户已经注册");
            }
            user.setId(oldUser.getId());
            if(userDao.updateById(user)!=1){
                //手动事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return JsonResult.err(205,"添加失败");
            }
            return JsonResult.ok();
        }

        user.setId(IdUtil.simpleUUID());
        //添加注册用户
        if(userDao.insert(user)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JsonResult.err(205,"添加失败");
        }
      //成功则删除key
        redisUtil.delphone(parMap.get("phone").toString());

        return JsonResult.ok();
    }

    @Override
    public JsonResult login(User user) {
        //登录逻辑

        //获取token
        String token = JwtUtil.getToken(user);

        //完成返回值的处理
        Map<String,Object> map = BeanUtil.beanToMap(user);
        map.put("token",token);
        map.put("password",null);
        return JsonResult.ok(map);
    }
}
 
