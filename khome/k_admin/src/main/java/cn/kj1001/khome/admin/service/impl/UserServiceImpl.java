package cn.kj1001.khome.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.dao.UserDao;
import cn.kj1001.khome.admin.service.UserService;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getList(Map<String, Object> parMap) {
        //创建sql模板 条件构造
        QueryWrapper<User> qw = new QueryWrapper<User>();

        //精确查询
        qw.eq("del_state",0);
        //模糊查询
        if(parMap.get("phone")!=null){
            qw.like("phone",parMap.get("phone"));
        }

        if (parMap.get("level")!=null){
            qw.like("levle",parMap.get("levle"));
        }
        return  userDao.selectList(qw);
    }

    //新增用户
    @Override
    public JsonResult addUser(User user) {
        //查询用户是否存在 不存在则添加 这里的查询用户是通过mapper的sql查询的
        List<User> list = userDao.selectListByEntity(
                new User().setPhone(user.getPhone()));
        if (list != null && list.size()>0) {
            return JsonResult.err(205,"电话已存在");
        }

        List<User> list1 = userDao.selectListByEntity(
                new User().setUserName(user.getUserName()));

        if (list1 != null && list1.size()>0) {
            return JsonResult.err(205,"用户名已存在");
        }
        //整合参数
        //使用hutool uuid工具类生成UUid
        user.setId(IdUtil.simpleUUID());
        user.setCreateTime(LocalDateTime.now());

        if(userDao.insert(user)!=1){
            //如果插入的条数不是1则进行事物回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JsonResult.err(205,"添加失败");
        }

        return JsonResult.ok();
    }

    //查询用户
    @Override
    public List<User> selectListByUser(Map<String, Object> parMap) {

//        User user = new  User().setPhone(parMap.get("phone").toString());
//        List<User> list = userDao.selectListByEntity(user);
//        //判断list
//        if (list==null||list.size()==0) {
//            return JsonResult.err(205,"用户不存在");
//        }
//
//        Map<String,Object> reMap = new HashMap();
//        reMap.put("user",list.get(0).setPassword(""));

        QueryWrapper<User> qw = new QueryWrapper<User>();

        if(parMap.get("phone")!=null){
            qw.eq("phone",parMap.get("phone"));
        }

        if(parMap.get("userName")!=null){
            qw.eq("user_name",parMap.get("userName"));
        }

       List<User> list = userDao.selectList(qw);
        list.forEach(user -> {
            user.setPassword(null);
        });
        return list;

    }


    //删除用户(修改用户状态)
    @Override
    @Transactional
    public JsonResult delUser(User user) {

        QueryWrapper<User> qw = new QueryWrapper<User>();
        //先判断用户是否存在
        if(user.getPhone()!=null){
            qw.eq("phone",user.getPhone());
        }
        List<User> list = userDao.selectList(qw);
        //
        if (list.isEmpty()||list==null){
            return JsonResult.err(205,"用户不存在");
        }
        //数据库字段del_state
        int update = userDao.update(user,qw);

        if(update!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JsonResult.err(205,"修改失败");
        }
        return JsonResult.ok("修改成功");
    }

    //修改用户会员等级
    @Override
    @Transactional
    public JsonResult updateUser(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();

        //查询用户
        if(user.getPhone()!=null){
            qw.eq("phone",user.getPhone());
        }

        int update = userDao.update(user,qw);
        if(update!=1){
            //添加事物回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JsonResult.err(205,"修改会员失败");
        }
        return JsonResult.ok("会员修改成功");
    }


}
