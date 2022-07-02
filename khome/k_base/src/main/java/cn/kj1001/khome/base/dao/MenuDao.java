package cn.kj1001.khome.base.dao;

import cn.kj1001.khome.base.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> selectMenuListByRoleId(String id);

}
