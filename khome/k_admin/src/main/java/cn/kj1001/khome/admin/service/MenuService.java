package cn.kj1001.khome.admin.service;

import cn.kj1001.khome.base.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
public interface MenuService extends IService<Menu> {
    //获取所有的角色菜单id
    List<Menu> getMenuListByRoleId(String id);

}
