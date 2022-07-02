package cn.kj1001.khome.admin.service.impl;

import cn.hutool.extra.ssh.JschRuntimeException;
import cn.kj1001.khome.base.entity.SubjectList;
import cn.kj1001.khome.base.dao.SubjectListDao;
import cn.kj1001.khome.admin.service.SubjectListService;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库列表表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class SubjectListServiceImpl extends ServiceImpl<SubjectListDao, SubjectList> implements SubjectListService {

    @Autowired
    SubjectListDao subjectListDao;

    /**
    * 参数介绍:
    * @description: 功能描述  查询列表
    * @author: MrZhang
    * @date: 2022/4/6 0:16
    * @param: [parmap]
    * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    **/
    @Override
    public List<Map<String,Object>> getSubjectList(Map<String, Object> parmap) {
        return subjectListDao.selectSubList(parmap);

    }
}
