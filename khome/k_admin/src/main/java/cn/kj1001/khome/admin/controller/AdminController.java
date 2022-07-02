package cn.kj1001.khome.admin.controller;


import cn.kj1001.khome.admin.service.AdminService;
import cn.kj1001.khome.admin.service.MenuService;
import cn.kj1001.khome.base.entity.Admin;
import cn.kj1001.khome.base.entity.Menu;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.PageUtils;
import cn.kj1001.khome.base.util.ParmUtil;
import com.mysql.cj.CacheAdapter;
import jdk.security.jarsigner.JarSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    MenuService menuService;


    /**
    * 参数介绍:
    * @description: 功能描述 登录权限
     * 1.完成登录操作
     * 2.根据登录后的权限id 可以访问的菜单
    * @author: MrZhang
    * @date: 2022/3/31 23:39
    * @param: [parMap]
    * @return: cn.kj1001.khome.base.util.JsonResult
    **/
    @GetMapping("login")
    public JsonResult login(@RequestParam Map<String,Object> parMap){
        //要验证的参数和名称
        Map<String,String> checkParMap = new HashMap<>(){
            {
                put("phone","手机号");
                put("password","密码");
            }
        };
        JsonResult jr = ParmUtil.check(checkParMap, parMap);
        if(jr!=null)  return jr;
        //调用service
        JsonResult jsonResult = adminService.login(parMap);
        if(jsonResult.getCode()!=200){
            return jsonResult;
        }
        //完成可访问菜单的信息的处理
        Map<String,Object> dataMap = (Map<String,Object>) jsonResult.getData();
        Admin admin = (Admin)dataMap.get("admin");
        List<Menu> menuList = menuService.getMenuListByRoleId(admin.getRoleId());
        dataMap.put("menuList",menuList);

        //返回结果
        return jsonResult;

    }
}

