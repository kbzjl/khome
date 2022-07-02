package cn.kj1001.khome.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.kj1001.khome.base.dao.UserDao;
import cn.kj1001.khome.base.entity.Info;
import cn.kj1001.khome.base.dao.InfoDao;
import cn.kj1001.khome.admin.service.InfoService;
import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家信息表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class InfoServiceImpl extends ServiceImpl<InfoDao, Info> implements InfoService {

    @Autowired
    InfoDao infoDao;

    @Autowired
    UserDao userDao;
    /**
    * 参数介绍:
    * @description: 功能描述 查询所有商家信息
    * @author: MrZhang
    * @date: 2022/3/23 15:22
    * @param: [parMap]
    * @return: java.util.List<cn.kj1001.khome.base.entity.Info>
    **/


    //查询所有商家信息
    @Override
    public List<Info> getList(Map<String, Object> parMap) {
        //创建sql模板 条件构造
        QueryWrapper<Info> qw = new QueryWrapper<Info>();
        qw.eq("del_state",0);
        if (parMap.get("userId")!=null){
            qw.eq("user_id",parMap.get("userId"));
        }
        if (parMap.get("stoName")!=null){
            qw.like("sto_name",parMap.get("stoName"));
        }
        if(parMap.get("phone")!=null){
            qw.like("phone",parMap.get("phone"));
        }
        if(parMap.get("qq")!=null){
            qw.like("qq",parMap.get("qq"));
        }
        if(parMap.get("wx")!=null){
            qw.like("wx",parMap.get("wx"));
        }
        if(parMap.get("info")!=null){
            qw.like("info",parMap.get("info"));
        }
        return infoDao.selectList(qw);

    }

    //查询商家信息
    @Override
    public List<Info> getSto(Map<String, Object> parMap) {
        //创建sql模板 条件构造
        QueryWrapper<Info> qw = new QueryWrapper<>();
        qw.eq("del_state",0);
        if(parMap.get("phone")!=null){
            qw.like("phone",parMap.get("phone"));
        }
        if(parMap.get("stoName")!=null){
            qw.like("sto_name",parMap.get("stoName"));
        }

        return infoDao.selectList(qw);
    }

    @Override
    @Transactional
    public JsonResult updateSto(Info info) {
        //创建sql模板
        QueryWrapper<Info> qw = new QueryWrapper<>();

        //精确查询手机号
        if(info.getPhone()!=null){
            qw.eq("phone",info.getPhone());
        }
        //查询qw对象中的手机信息 并存入list
        List<Info> list = infoDao.selectList(qw);
        if (list==null||list.isEmpty()){
            return JsonResult.err(205,"用户不存在");
        }
        //根据查询的手机号去修改对象的商家信息
        int update = infoDao.update(info,qw);
        if(update!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            JsonResult.err(205,"商家信息修改失败");
        }
        return JsonResult.ok("商家信息修改成功");
    }


    //添加商家信息
    @Override
    @Transactional
    public JsonResult addSto(Info info) {
        //创建sql模板
        QueryWrapper<Info> qw = new QueryWrapper<Info>();
        if(info.getPhone()!=null){
            qw.eq("phone",info.getPhone());
        }
        //添加之前先去查询商家是否存在
        List<Info> list = infoDao.selectList(qw);
        if (list==null||list.isEmpty()){
            return JsonResult.err(205,"商家用户不存在");
        }

        //在这里需要存入用户user_id不会处理

        //应该是先去user表通过手机号查询到对应的用户id
        // 在获取用户修改后的认证的状态是否是商家用户
        //如果是商家 将id存入商家信息

        info.setId(IdUtil.simpleUUID());
        info.setCreateTime(LocalDateTime.now());
        if(infoDao.insert(info)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JsonResult.err(205,"商家添加失败");

        }
        return JsonResult.ok();
    }
}
