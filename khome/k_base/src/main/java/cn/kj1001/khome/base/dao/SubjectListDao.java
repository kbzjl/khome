package cn.kj1001.khome.base.dao;

import cn.kj1001.khome.base.entity.SubjectList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题库列表表 Mapper 接口
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface SubjectListDao extends BaseMapper<SubjectList> {
    List<Map<String,Object>> selectSubList(Map<String,Object> parMap);


}
