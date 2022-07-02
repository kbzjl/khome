package cn.kj1001.khome.admin.service.impl;

import cn.kj1001.khome.base.entity.Menu;
import cn.kj1001.khome.base.dao.MenuDao;
import cn.kj1001.khome.admin.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> getMenuListByRoleId(String id) {
        //查询结果 如果id是0是管理员拥有所有权限给他一个null 如果不是0则把当前的id赋值给他
        List<Menu> menuList = menuDao.selectMenuListByRoleId(id.equals("0")?null:id);
        //如果没有查到menu则返回null
        if(menuList==null){
            return null;
        }
        //完成menu结构的处理 获取可以访问的父级菜单
        List<Menu> perMenuList = menuList.stream().filter(m ->{
            return m.getBaseId().equals("0");
        }).collect(Collectors.toList());

        //遍历父菜单-过滤出子菜单并存入
        for(Menu menu:perMenuList){
            List<Menu> menus = menuList.stream().filter(m ->{
                return  m.getBaseId().equals(menu.getId());
            }).collect(Collectors.toList());
            menu.setChildMenuList(menus);

        }
        return perMenuList;
    }
}
