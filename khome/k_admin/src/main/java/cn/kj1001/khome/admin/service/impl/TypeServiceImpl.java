package cn.kj1001.khome.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.kj1001.khome.base.entity.Type;
import cn.kj1001.khome.base.dao.TypeDao;
import cn.kj1001.khome.admin.service.TypeService;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.security.jarsigner.JarSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库类型表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
@Transactional
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements TypeService {

    @Autowired
    TypeDao typeDao;

    /**
    * 参数介绍:
    * @description: 功能描述 查询题库
    * @author: MrZhang
    * @date: 2022/3/29 21:17
    * @param: [parMap]
    * @return: java.util.List<cn.kj1001.khome.base.entity.Type>
    **/
    @Override
    public List<Type> selectList(Map<String, Object> parMap) {
        //创建模板
        QueryWrapper qw = new QueryWrapper();
        qw.eq("del_state",0);

        //根据题库名称查询查询题库类型
        if(parMap.get("typeName")!=null){
            qw.like("type_name",parMap.get("typeName"));
        }

        List<Type> list = typeDao.selectList(qw);
        if(list.isEmpty()||list==null){
            JsonResult.err(205,"您所查询的题库类型名称不存在");
        }
        return list;
    }


    /**
    * 参数介绍:
    * @description: 功能描述 添加题库类型
    * @author: MrZhang
    * @date: 2022/3/29 21:40
    * @param: [type]
    * @return: cn.kj1001.khome.base.util.JsonResult
    **/
    @Override
    public JsonResult addType(Type type) {
        QueryWrapper<Type> qw = new QueryWrapper<>();
        qw.eq("del_state",0);
        if(type.getTypeName()!=null||type.getTypeName().equals("")){
            qw.eq("type_name",type.getTypeName());
        }
        List<Type> list = typeDao.selectList(qw);
        if(!(list==null||list.isEmpty())){
            return JsonResult.err(205,"该题库已存在");
        }
        //为空 则添加ID 和时间
        type.setId(IdUtil.simpleUUID());
        type.setCreateTime(LocalDateTime.now());

        //如果插入的条数不为1 则事务回滚
        if(typeDao.insert(type)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            JsonResult.err(205,"添加题库类型失败");
        }
        return JsonResult.ok("添加成功");
    }


    @Override
    public JsonResult updateType(Type type) {
        QueryWrapper<Type> qw = new QueryWrapper<Type>();
        //qw.eq("del_state",0);
        qw.eq("id",type.getId());

        List<Type> list = typeDao.selectList(qw);
        if(list.isEmpty()||list==null){
            JsonResult.err(205,"题库类型不存在");
        }
        //进行修改操作
        int update = typeDao.update(type,qw);
        if(update!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return JsonResult.err(500,"修改失败");

        }
        return JsonResult.ok("修改成功");
    }
}
