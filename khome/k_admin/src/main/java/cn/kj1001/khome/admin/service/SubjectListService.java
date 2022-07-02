package cn.kj1001.khome.admin.service;

import cn.kj1001.khome.base.entity.SubjectList;
import cn.kj1001.khome.base.util.JsonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库列表表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface SubjectListService extends IService<SubjectList> {
    //查看题库列表
    List<Map<String,Object>> getSubjectList(Map<String,Object> parmap);

}
