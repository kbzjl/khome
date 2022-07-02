package cn.kj1001.khome.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.kj1001.khome.admin.service.UserService;
import cn.kj1001.khome.base.entity.User;
import cn.kj1001.khome.base.util.JsonResult;
import cn.kj1001.khome.base.util.PageUtils;
import cn.kj1001.khome.base.util.ParmUtil;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author kj1001
 * @since 2022-03-21
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
    * 参数介绍:
    * @description: 功能描述 验证查询参数 整合分页参数并返回前端
    * @author: MrZhang
    * @date: 2022/3/21 18:27
    * @param: [parMap]
    * @return: cn.kj1001.khome.base.util.JsonResult
    **/
    @ApiOperation("查看用户列表")
    @ApiImplicitParam(name = "parMap",
            value = "必填参数,查询的信息",
            defaultValue = "{'pageName:1,pageSize:5'}")

    @GetMapping("list")
    public JsonResult list(@RequestParam Map<String,Object> parMap){
//        //参数验证
//        Integer pageNum = 1;
//        Integer pageSize = 10;
//        if(parMap.get("pageNum")!=null){
//            pageNum = Integer.valueOf(parMap.get("pageNum").toString());
//        }
//        if(parMap.get("pageSize")!=null){
//            pageSize = Integer.valueOf(parMap.get("pageSize").toString());
//        }
//
//        //开启分页拦截器 传入当前页和每页显示的条数
//        PageHelper.startPage(pageNum,pageSize);

        //使用封装的分页工具类
        PageUtils.pageNums(parMap);
        //完成查询并判断
        List<User> list = userService.getList(parMap);
        if(list==null||list.isEmpty()){
            return JsonResult.err(205,"数据不存在");
        }
        //整合分页并返回
        PageInfo<User>  pageInfo = new PageInfo<User>(list);
        return JsonResult.ok(pageInfo);

    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({

            @ApiImplicitParam(paramType = "query",name = "password",value = "密码",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "phone",value = "手机号",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "userName",value = "用户名",dataType = "String")
    })
    @PostMapping("add")
    public JsonResult add(@RequestParam Map<String,Object>parMap){
//        if (parMap.get("phone")==null||parMap.get("phone").equals("")){
//            return JsonResult.err(201,"手机号码不能为空");
//
//        }
//
//        if (parMap.get("password")==null||parMap.get("password").equals("")){
//            return JsonResult.err(201,"密码不能为空");
//
//        }
//
//        if (parMap.get("userName")==null||parMap.get("userName").equals("")){
//            return JsonResult.err(201,"用户名不能为空");
//        }


        //使用统一验证参数
        Map<String,String> checkParMap = new HashMap<>(){
            {
                put("phone","手机号");
                put("password","用户密码");
                put("userName","用户名");
            }
        };

        //调用封装的参数验证工具类
        JsonResult jr = ParmUtil.check(checkParMap, parMap);
        if(jr!=null)  return jr;

        //使用工具将map转为bean
        User user = BeanUtil.toBean(parMap,User.class);
        //调用service方法添加用户
        return userService.addUser(user);

    }
    @GetMapping("info")
    public JsonResult getUserInfo(@RequestParam Map<String,Object>parMap){
//        if (parMap.get("phone")==null||parMap.get("phone").equals("")){
//            return JsonResult.err(201,"手机号码不能为空");
//
//        }

        //参数验证
        Integer pageNum = 1;
        Integer pageSize = 10;
        if(parMap.get("pageNum")!=null){
            pageNum = Integer.valueOf(parMap.get("pageNum").toString());
        }
        if(parMap.get("pageSize")!=null){
            pageSize = Integer.valueOf(parMap.get("pageSize").toString());
        }

        //开启分页拦截器 传入当前页和每页显示的条数
        PageHelper.startPage(pageNum,pageSize);
        //完成查询并判断
        List<User> list = userService.selectListByUser(parMap);
        if(list==null||list.isEmpty()){
            return JsonResult.err(205,"数据不存在");
        }

        //整合分页并返回
        PageInfo<User>  pageInfo = new PageInfo<User>(list);
        return JsonResult.ok(pageInfo);

    }

    //删除用户状态
    @PostMapping("delUser")
    public JsonResult delUser(@RequestParam Map<String,Object> map){
        if(map.get("phone")==null||map.get("phone").equals("")){
            return JsonResult.err(201,"参数为传入");
        }
        if(map.get("delState")==null||map.get("delState").equals("")){
            return JsonResult.err(201,"状态参数为传入");
        }

        User user = BeanUtil.toBean(map, User.class);
       return  userService.delUser(user);

    }

    //修改会员等级
    @PostMapping("updateUser")
    public JsonResult updateUser(@RequestParam Map<String,Object> map){
        if(map.get("phone")==null||map.get("phone").equals("")){
            return JsonResult.err(201,"参数为传入");
        }
        if(map.get("level")==null||map.get("level").equals("")){
            return JsonResult.err(201,"状态参数为传入");
        }
        User user = BeanUtil.toBean(map, User.class);
        return userService.updateUser(user);

    }

}

